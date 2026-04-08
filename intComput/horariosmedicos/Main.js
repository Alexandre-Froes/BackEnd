document.addEventListener('DOMContentLoaded', () => {

    let populacao = new Array();
    let notas = new Array();
    // const medicos = new Array();

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

    // function geraMedico() {
    //     const especialidades = [
    //         'CG', 'PE', 'GI', 'OR', 'CA'
    //     ]

    //     for(var i = 0; i<25;++i) {
    //         const random0a4 = Math.floor(Math.random() * 5);
    //         let medico = {
    //             id: i,
    //             especialidade: especialidades[random0a4]
    //         }
    //         medicos.add(medico)
    //     }
    // }

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

    function avaliacao(cromossomo) {
        let softPen = 1;
        let mediumPen = softPen * 3;
        let hardPen = softPen * 6;

        let penTotal = 0;
        let medicoTurnoAnt = []; // qual médico estava antes em cada turno

        for (let i = 0; i < cromossomo.length; i += 27) {
            const medicosDoDia = cromossomo.slice(i, i + 27);

            // Validamos cada uma das 3 unidades no dia
            for (let u = 0; u < 3; u++) {

                const m1 = medicosDoDia[u * 3 + 0], m2 = medicosDoDia[u * 3 + 1], m3 = medicosDoDia[u * 3 + 2]; // Manhã
                const t1 = medicosDoDia[u * 3 + 9], t2 = medicosDoDia[u * 3 + 10], t3 = medicosDoDia[u * 3 + 11]; // Tarde
                const n1 = medicosDoDia[u * 3 + 18], n2 = medicosDoDia[u * 3 + 19], n3 = medicosDoDia[u * 3 + 20]; // Noite

                const escalaDaUnidadeNoDia = [m1, m2, m3, t1, t2, t3, n1, n2, n3];

                // Mapeamos os IDs para suas Especialidades
                const especialidadesNoDia = escalaDaUnidadeNoDia.map(id => medicos[id].especialidade);
                
                const diversidade = new Set(especialidadesNoDia).size;

                if (diversidade < 5) {

                    // TODO: tem que ver esse peso aqui
                    penTotal += mediumPen; 
                }
            }
        }

        for (let i = 0; i < cromossomo.length; i += 9) {
            const medicosTurnoAtual = cromossomo.slice(i, i + 9);

            // se tiver menos de 9 médicos ele pega o resto e faz * 500
            // no set n repete coisa ent se tiver menos é pq tem repetido
            penTotal += (9 - new Set(medicosTurnoAtual).size) * hardPen;

            // turno sem clinico geral
            for (let un = 0; un < 9; un += 3) {
                const unidade = medicosTurnoAtual.slice(un, un + 3);
                
                let temCG = unidade.some(id => medicos[id].especialidade === 'CG');
                if (!temCG) penTotal += mediumPen;
            }

            // turno tem medico consecutivo
            if (medicoTurnoAnt.length > 0) {
                medicosTurnoAtual.forEach(id => {
                    if (medicoTurnoAnt.includes(id)) penTotal += softPen;
                });
            }

            medicoTurnoAnt = medicosTurnoAtual;
        }

        return penTotal;
    }


    function avaliaCromossomo() {
        for (const cromossomo of populacao) {
            notas.push(avaliacao(cromossomo));
        }

        return notas.sort();
    }


    geraPopulacao(100);
    avaliaCromossomo();

    console.log(notas);
});
