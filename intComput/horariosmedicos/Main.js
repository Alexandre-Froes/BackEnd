document.addEventListener('DOMContentLoaded', () => {
    let populacao = new Array();
    let notas = new Array();

    const medicos = [
        // 5 Clínicos Gerais (CG)
        { id: 0, especialidade: "CG" },
        { id: 1, especialidade: "CG" },
        { id: 2, especialidade: "CG" },
        { id: 3, especialidade: "CG" },
        { id: 4, especialidade: "CG" },

        // 5 Pediatras (PE)
        { id: 5, especialidade: "PE" },
        { id: 6, especialidade: "PE" },
        { id: 7, especialidade: "PE" },
        { id: 8, especialidade: "PE" },
        { id: 9, especialidade: "PE" },

        // 5 Ginecologistas (GI)
        { id: 10, especialidade: "GI" },
        { id: 11, especialidade: "GI" },
        { id: 12, especialidade: "GI" },
        { id: 13, especialidade: "GI" },
        { id: 14, especialidade: "GI" },

        // 5 Ortopedistas (OR)
        { id: 15, especialidade: "OR" },
        { id: 16, especialidade: "OR" },
        { id: 17, especialidade: "OR" },
        { id: 18, especialidade: "OR" },
        { id: 19, especialidade: "OR" },

        // 5 Cardiologistas (CA)
        { id: 20, especialidade: "CA" },
        { id: 21, especialidade: "CA" },
        { id: 22, especialidade: "CA" },
        { id: 23, especialidade: "CA" },
        { id: 24, especialidade: "CA" }
    ];

    const getRandomInt = (max) => Math.floor(Math.random() * max);

    console.log(populacao);

    function geraPopulacao(popInicial) {
        for (var i = 0; i < popInicial; ++i) {
            let cromossomo = [];
            for (var j = 0; j < 189; ++j) {
                let randomId = getRandomInt(25);
                cromossomo.push(randomId);
            }
            populacao.push(cromossomo);
        }
    }

    function avaliacao(cromossomo, config) {
        let penTotal = 0;
        const turnosPorMedico = new Uint8Array(25);
        let idsTurnoAnterior = new Set();

        // LOOP DE DIAS
        for (let idDia = 0; idDia < cromossomo.length; idDia += 27) {
            
            // LOOP DE TURNOS
            for (let j = 0; j < 27; j += 9) {
                const idTurno = idDia + j;
                const vistosNoTurno = new Set();

                // LOOP DE UNIDADES
                for (let k = 0; k < 9; k += 3) {
                    const idUnidade = idTurno + k;
                    
                    let contagemCG = 0;
                    const medicosNaUnidade = new Set();

                    // COLETA DE DADOS DA UNIDADE
                    for (let m = 0; m < 3; m++) {
                        const idMed = cromossomo[idUnidade + m];

                        turnosPorMedico[idMed]++;

                        if (medicos[idMed].especialidade === 'CG') {
                            contagemCG++;
                        }

                        if (idsTurnoAnterior.has(idMed)) {
                            penTotal += config.hardPen; 
                        }

                        medicosNaUnidade.add(idMed);
                        vistosNoTurno.add(idMed);
                    }

                    penTotal += regraClinicoGeralUnidade(contagemCG, config);
                    penTotal += regraMedicosDistintosUnidade(medicosNaUnidade.size, config);
                }

                penTotal += regraConflitoInternoTurno(vistosNoTurno.size, config);

                idsTurnoAnterior = vistosNoTurno;
            }
        }

        penTotal += regraCargaHorariaSemanal(turnosPorMedico, config);

        return penTotal;
    }

    function regraClinicoGeralUnidade(contagemCG, config) {
        return contagemCG < 1 ? config.mediumPen : 0;
    }

    function regraMedicosDistintosUnidade(totalDistintosNaUnidade, config) {
        if (totalDistintosNaUnidade < 3) {
            return (3 - totalDistintosNaUnidade) * config.hardPen;
        }
        return 0;
    }

    function regraConflitoInternoTurno(totalNoTurno, config) {
        if (totalNoTurno < 9) return (9 - totalNoTurno) * config.hardPen;
        return 0;
    }

    // considerei que cada turno tem 8 horas (por mais que n faça mto sentido)
    function regraCargaHorariaSemanal(turnosPorMedico, config) {
        let p = 0;
        for (let i = 0; i < turnosPorMedico.length; i++) {
            if (turnosPorMedico[i] > 5) {
                p += (turnosPorMedico[i] - 5) * config.heavyPen;
            }
        }
        return p;
    }

    function avaliaCromossomo() {
        const CONFIGPEN = {
            softPen: 1,
            mediumPen: 4,
            hardPen: 8,
            heavyPen: 15
        }

        for (const cromossomo of populacao) {
            notas.push(avaliacao(cromossomo, CONFIGPEN));
        }

        return notas.sort((a, b) => a - b);
    }

    geraPopulacao(100);
    avaliaCromossomo();

    console.table(populacao);
    console.table(notas);
});