
# Dataset bruto (lista de dicionários)
dados_pesquisa = [
{'id_cliente': 1, 'idade': 35, 'genero': 'Feminino', 'escolaridade':
'Superior', 'renda_mensal': 5200.0, 'avaliacao': 4, 'cidade': 'Uberlândia'}, 

{'id_cliente': 2, 'idade': -42, 'genero': 'Masculino', 'escolaridade':
'Médio', 'renda_mensal': 4800.0, 'avaliacao': 5, 'cidade': 'Uberlândia'}, # idade inválida (ruído)

{'id_cliente': 3, 'idade': '28','genero': 'Feminino', 'escolaridade':
None, 'renda_mensal': None, 'avaliacao': None, 'cidade': 'Ituiutaba'}, # Idade, renda e escoladidade ausentes

{'id_cliente': 4, 'idade': 55, 'genero': 'Masculino', 'escolaridade':
'Superior', 'renda_mensal': 12000.0, 'avaliacao': 3, 'cidade': 'Patos de Minas'},

{'id_cliente': 5, 'idade': 150, 'genero': 'Feminino', 'escolaridade':
'Mestrado', 'renda_mensal': 999999.0, 'avaliacao': 5, 'cidade': 'Uberaba'}, # idade e renda outlier

{'id_cliente': 6, 'idade': 45, 'genero': 'Não-binário','escolaridade':
'Superior', 'renda_mensal': 6500.0, 'avaliacao': 4, 'cidade': 'Uberlândia'},

{'id_cliente': 7, 'idade': 33, 'genero': 'Masculino', 'escolaridade':
'Fundamental','renda_mensal': 2100.0, 'avaliacao': None, 'cidade': 'Araguari'}, # avaliação ausente

{'id_cliente': 8, 'idade': 68, 'genero': 'Feminino', 'escolaridade':
'Médio', 'renda_mensal': 3200.0, 'avaliacao': 2, 'cidade': 'Uberlândia'},

{'id_cliente': 9, 'idade': 22, 'genero': 'Feminino', 'escolaridade':
'Superior', 'renda_mensal': -100.0, 'avaliacao': 4, 'cidade': 'Uberlândia'}, # renda inválida (ruído)

{'id_cliente':10, 'idade': None,'genero': 'Masculino', 'escolaridade':
'Médio', 'renda_mensal': 4100.0, 'avaliacao': 3, 'cidade': 'Ituiutaba'}, # idade ausente

{'id_cliente':11, 'idade': 41, 'genero': 'Masculino', 'escolaridade': 
'Doutorado', 'renda_mensal': 18000.0, 'avaliacao': 5, 'cidade': 'Uberlândia'},

{'id_cliente':12, 'idade': 29, 'genero': 'Feminino', 'escolaridade':
'Superior', 'renda_mensal': 5600.0, 'avaliacao': 4, 'cidade': 'Uberaba'},
]

### 1) Inspeção e diagnóstico (obrigatório)
# 1. Imprima os registros.
# 2. Em comentário, liste **pelo menos 5 problemas** encontrados e classifique como:
# - ruído, outlier, ausente, tipo incorreto.
# 3. Em comentário, classifique os atributos (nominal/ordinal/numérico) e diga **qual seria
# o impacto** de representar errado em uma análise de dados.

for registro in dados_pesquisa:
    print(registro)

# {
# 'id_cliente':12 Numerico, 
# 'idade': 29 Numerico,
# 'genero': 'Feminino' Nominal,
# 'escolaridade':'Superior' Ordinal,
# 'renda_mensal': 5600.0 Numerico,
#  'avaliacao': 4 Ordinal,
#  'cidade': 'Uberaba' Nominal
# },


def to_int_safe(valor):
    return isinstance(valor, int) and valor or None

def to_float_safe(valor):
    return isinstance(valor, float) and valor or None

def valida_idade(idade, max = 120, min = 0):
    idade_int = to_int_safe(idade)
    return isinstance(idade_int, int) and min <= idade_int <= max and idade_int or None

def valida_renda(renda, max = 50000, min = 0):
    renda_float = to_float_safe(renda)
    return isinstance(renda_float, float) and min <= renda_float <= max and renda_float or None


idade_invalida = 0
renda_invalida = 0
def registro_valido(registro):
    if valida_idade(registro.get('idade')) is None:
        global idade_invalida
        idade_invalida += 1
        return False
    if valida_renda(registro.get('renda_mensal')) is None:
        global renda_invalida
        renda_invalida += 1
        return False
    return True 

dados_limpo = []

n_removidos = 0
total_registros_limpos = 0
soma_renda = 0
soma_escolaridade_ordinal = 0

def escolaridade_para_ordinal(registro):
    mapping = {
        'Fundamental': 1,
        'Médio': 2,
        'Superior': 3,
        'Mestrado': 4,
        'Doutorado': 5,
    }

    registro['escolaridade_ordinal'] = mapping.get(registro.get('escolaridade'), None)

def idade_para_range(registro):
    idade = registro.get('idade')
    if idade < 18:
        registro['faixa_idade'] = '0-17'
    elif idade < 26:
        registro['faixa_idade'] = '18-25'
    elif idade < 36:
        registro['faixa_idade'] = '26-35'
    elif idade < 51:
        registro['faixa_idade'] = '36-50'
    else:
        registro['faixa_idade'] = '51+'

for registro in dados_pesquisa:
    if registro_valido(registro):
        escolaridade_para_ordinal(registro)
        idade_para_range(registro)
        dados_limpo.append(registro)

        soma_escolaridade_ordinal += registro.get('escolaridade_ordinal', 0) or 0
        soma_renda += registro.get('renda_mensal', 0) or 0
    else:
        n_removidos += 1

    total_registros_limpos += 1

for registro in dados_limpo:
    print(registro)

print(f"Registros removidos: {n_removidos}")
print(f"Idades inválidas: {idade_invalida}")
print(f"Rendas inválidas: {renda_invalida}")






