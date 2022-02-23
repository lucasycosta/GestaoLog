#language:pt
Funcionalidade: Gerar grafico de quantidade de acesso de cada nivel de acesso

	Cenario: Gerar um grafico que traga informacoes da quantidade de acesso dos nivel de acesso
		Dado usuario logado como gerente na pagina de registro e na pagina de registro 
		Quando clicar no botao que direciona para a pagina de graficos 
		E selecionar um intervalo de datas
		Entao sera apresentado um grafico com a quantidade de acesso dos nivel de acesso