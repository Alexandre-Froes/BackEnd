function inicializarAlgoritmo() {
    let populacao = new Array();
    let notas = new Array();

    const medicos = gerarMedicos(9);

    function gerarMedicos(qtdCG) {
        const TOTAL_MEDICOS = 25;

        if (qtdCG > TOTAL_MEDICOS) {
            throw new Error("Quantidade de CG maior que o total de médicos");
        }

        const especialidadesOrdem = ["PE", "GI", "OR", "CA"];

        const medicos = [];
        let id = 0;

        for (let i = 0; i < qtdCG; i++) {
            medicos.push({ id: id++, especialidade: "CG" });
        }

        let restante = TOTAL_MEDICOS - qtdCG;
        let idxEsp = 0;

        while (restante > 0) {
            medicos.push({
                id: id++,
                especialidade: especialidadesOrdem[idxEsp]
            });

            restante--;
            idxEsp = (idxEsp + 1) % especialidadesOrdem.length;
        }

        return medicos;
    }

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
        let penalidades = {
            repeticaoTurno: 0,
            faltaClinico: 0,
            medicosRepetidosUnidade: 0,
            conflitoTurno: 0,
            cargaHoraria: 0,
            total: 0
        };

        const turnosPorMedico = new Uint8Array(25);
        let idsTurnoAnterior = new Set();

        for (let idDia = 0; idDia < cromossomo.length; idDia += 27) {
            
            for (let j = 0; j < 27; j += 9) {
                const vistosNoTurno = new Set();

                for (let k = 0; k < 9; k += 3) {
                    let contagemCG = 0;
                    const medicosNaUnidade = new Set();

                    for (let m = 0; m < 3; m++) {
                        const idMed = cromossomo[idDia + j + k + m];

                        turnosPorMedico[idMed]++;

                        if (medicos[idMed].especialidade === 'CG') {
                            contagemCG++;
                        }

                        regraRepeticaoTurno(idMed, idsTurnoAnterior, penalidades, config);

                        medicosNaUnidade.add(idMed);
                        vistosNoTurno.add(idMed);
                    }

                    regraClinicoGeralUnidade(contagemCG, penalidades, config);
                    regraMedicosDistintosUnidade(medicosNaUnidade.size, penalidades, config);
                }

                regraConflitoInternoTurno(vistosNoTurno.size, penalidades, config);

                idsTurnoAnterior = vistosNoTurno;
            }
        }

        regraCargaHorariaSemanal(turnosPorMedico, penalidades, config);

        penalidades.total =
            penalidades.repeticaoTurno +
            penalidades.faltaClinico +
            penalidades.medicosRepetidosUnidade +
            penalidades.conflitoTurno +
            penalidades.cargaHoraria;

        return penalidades;
    }

    function regraRepeticaoTurno(idMed, idsTurnoAnterior, penalidades, config) {
        if (idsTurnoAnterior.has(idMed)) {
            penalidades.repeticaoTurno += config.heavyPen * 3;
        }
    }   

    function regraClinicoGeralUnidade(contagemCG, penalidades, config) {
        if (contagemCG === 0) {
            penalidades.faltaClinico += config.heavyPen;
        }

        if (contagemCG > 1) {
            penalidades.faltaClinico += (contagemCG - 1) * config.mediumPen;
        }
    }

    function regraMedicosDistintosUnidade(total, penalidades, config) {
        if (total < 3) {
            penalidades.medicosRepetidosUnidade += 
                (3 - total) * config.hardPen;
        }
    }

    function regraConflitoInternoTurno(total, penalidades, config) {
        if (total < 9) {
            penalidades.conflitoTurno += 
                (9 - total) * config.hardPen;
        }
    }

    function regraCargaHorariaSemanal(turnosPorMedico, penalidades, config) {
        let turnosMax = 7;

        for (let i = 0; i < turnosPorMedico.length; i++) {
            if (turnosPorMedico[i] > turnosMax) {
                penalidades.cargaHoraria += 
                    Math.round((turnosPorMedico[i] - turnosMax) * config.mediumPen, 0);
            }
        }
    }

    function avaliaCromossomo() {
        const CONFIGPEN = {
            softPen: 1,
            mediumPen: 4,
            hardPen: 8,
            heavyPen: 16
        }

        let penalidades = {
            repeticaoTurno: 0,
            faltaClinico: 0,
            medicosRepetidosUnidade: 0,
            conflitoTurno: 0,
            cargaHoraria: 0,
            total: 0
        }; 

        let populacaoComNotas = new Array();

        for (const cromossomo of populacao) {
            const resultado = avaliacao(cromossomo, CONFIGPEN);

            populacaoComNotas.push({
                cromossomo,
                nota: resultado.total,
                detalhes: resultado
            });
        }

        return populacaoComNotas.sort((a, b) => a.nota - b.nota);
    }

    function selecaoTorneio(populacaoComNotas, tamanhoTorneio = 10) {
        let melhorIdx = Math.floor(Math.random() * populacaoComNotas.length);
        
        for (let i = 1; i < tamanhoTorneio; i++) {
            const randomIdx = Math.floor(Math.random() * populacaoComNotas.length);
            if (populacaoComNotas[randomIdx].nota < populacaoComNotas[melhorIdx].nota) {
                melhorIdx = randomIdx;
            }
        }
        
        return populacaoComNotas[melhorIdx].cromossomo;
    }

    function selecaoRoleta(populacaoComNotas) {
        // Inverte as notas para que maiores valores = melhor fitness
        const maxNota = Math.max(...populacaoComNotas.map(p => p.nota));
        const fitnessInvertido = populacaoComNotas.map(p => maxNota - p.nota + 1);
        const somaFitness = fitnessInvertido.reduce((a, b) => a + b, 0);
        
        let random = Math.random() * somaFitness;
        for (let i = 0; i < populacaoComNotas.length; i++) {
            random -= fitnessInvertido[i];
            if (random <= 0) {
                return populacaoComNotas[i].cromossomo;
            }
        }
        
        return populacaoComNotas[populacaoComNotas.length - 1].cromossomo;
    }

    // ==================== CROSSOVER ====================
    function crossoverPorTurno(pai1, pai2) {
        const filho1 = [];
        const filho2 = [];

        let crossover = 9;

        for (let i = 0; i < pai1.length; i += crossover) {
            if (Math.random() < 0.5) {
                filho1.push(...pai1.slice(i, i + crossover));
                filho2.push(...pai2.slice(i, i + crossover));
            } else {
                filho1.push(...pai2.slice(i, i + crossover));
                filho2.push(...pai1.slice(i, i + crossover));
            }
        }

        return [filho1, filho2];
    }

    // ==================== MUTAÇÃO ====================
    function mutacao(cromossomo, probMutacao) {
        const cromossomoMutado = [...cromossomo];
        
        for (let i = 0; i < cromossomoMutado.length; i++) {
            if (Math.random() < probMutacao) {
                cromossomoMutado[i] = getRandomInt(25);
            }
        }
        
        return cromossomoMutado;
    }

    // ==================== ELITISMO ====================
    function aplicaElitismo(populacaoAtual, populacaoComNotas, numElites) {
        const melhores = populacaoComNotas.slice(0, numElites);
        const novaPopulacao = populacaoAtual.slice(numElites);
        
        return [...melhores.map(p => p.cromossomo), ...novaPopulacao];
    }

    // ==================== LOOP PRINCIPAL DO ALGORITMO GENÉTICO ====================
    function executarAlgoritmoGenetico() {
        const CONFIGPEN = {
            softPen: 1,
            mediumPen: 8,
            hardPen: 40,
            heavyPen: 120
        };

        const popInicial = 100;
        const probMutacao = 0.04;
        const probCrossover = 0.7;
        const numGeracoes = 1000;
        const comElitismo = true;
        const numElites = 7;

        geraPopulacao(popInicial);

        let melhorSolucao = null;

        for (let geracao = 0; geracao < numGeracoes; geracao++) {
            populacao = populacao.slice(0, popInicial);
            const populacaoComNotas = avaliaCromossomo();

            const melhorDaGeracao = populacaoComNotas[0];

            if (!melhorSolucao || melhorDaGeracao.nota < melhorSolucao.nota) {
                melhorSolucao = { ...melhorDaGeracao, geracao };
            }

            let novaPopulacao = [];

            if (comElitismo) {
                const melhores = populacaoComNotas.slice(0, numElites);
                novaPopulacao = [...melhores.map(p => p.cromossomo)];
            }

            while (novaPopulacao.length < popInicial) {
                const pai1 = selecaoTorneio(populacaoComNotas);
                const pai2 = selecaoTorneio(populacaoComNotas);

                let filho1 = pai1;
                let filho2 = pai2;

                if (Math.random() < probCrossover) {
                    [filho1, filho2] = crossoverPorTurno(pai1, pai2);
                }

                filho1 = mutacao(filho1, probMutacao);
                filho2 = mutacao(filho2, probMutacao);

                novaPopulacao.push(filho1);
                if (novaPopulacao.length < popInicial) {
                    novaPopulacao.push(filho2);
                }
            }

            populacao = novaPopulacao.slice(0, popInicial);
        }

            console.log("Melhor solução:");
            console.log("Geração:", melhorSolucao.geracao + 1);
            console.log("Fitness:", melhorSolucao.nota);
            console.log("Detalhes:");
            console.table(melhorSolucao.detalhes);
    }

    // ==================== TESTES E EXECUÇÃO ====================
    populacao = [];
    const solucaoFinal = executarAlgoritmoGenetico();

    return solucaoFinal;

    
}

// ==================== EXECUÇÃO ====================
console.log("SISTEMA DE ESCALONAMENTO DE MEDICOS COM ALGORITMO GENETICO");
console.log("");

const resultado = inicializarAlgoritmo();

if (typeof module !== 'undefined' && module.exports) {
    module.exports = { inicializarAlgoritmo, resultado };
}