#language:pt
Funcionalidade: Buscar registros com filtros

	Cenario: Buscar um registro especifico atraves do nome do usuario
		Dado usuario  logado como gerente na pagina de registro
		Quando preencher o campo "Nome de Usuario"
			#E clicar em "pesquisa"
		Entao os registro com aquele nome de usuario serão apresentados 
		
	Cenario: Buscar um registro especifico atraves do id do usuario
		Dado usuario  logado como gerente na pagina de registro
		Quando preencher o campo "id de Usuario"
			#E clicar em "pesquisa"
		Entao os registro com aquele id de usuario serão apresentados 
	
	Cenario: Buscar um registro especifico atraves do email do usuario
		Dado usuario  logado como gerente na pagina de registro
		Quando preencher o campo "E-mail"
			#E clicar em "pesquisa"
		Entao os registro com aquele email serão apresentados 
		
	Cenario: Buscar um registro especifico atraves do nivel de acesso
		Dado usuario  logado como gerente na pagina de registro
		Quando selecionar um nivel no campo "Nivel de Acesso"
			#E clicar em "pesquisa"
		Entao os registro com aquele nivel de acesso serão apresentados 
	
	Cenario: Buscar um registro especifico atraves de uma funcionalidade
		Dado usuario  logado como gerente na pagina de registro
		Quando selecionar uma funcionalidade no campo "Funcionalidade"
			#E clicar em "pesquisa"
		Entao os registro com aquel funcionalidade serão apresentados 
		
	Cenario: Buscar um registro especifico atraves da data
		Dado usuario  logado como gerente na pagina de registro
		Quando selecionar um intervalo de data 
			#E clicar em "pesquisa"
		Entao os registro daquele intervalo de data serão apresentados 
		
	