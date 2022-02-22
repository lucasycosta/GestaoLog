#language:pt
Funcionalidade: Cadastro de registro 
	
	Cenario: Cadastrar registro com idUsuario vazio
		Dado que o usuario seja cadastrado no sistema
		Quando eu realizo uma ação no sistema 
		Entao o sistema registra minha ação sem capturar meu id
		
	Cenario: Cadastrar registro com nomeUsuario vazio
		Dado que o usuario seja cadastrado no sistema
		Quando eu realizo uma ação no sistema 
		Entao o sistema registra minha ação sem capturar meu nome de usuario
	
	Cenario: Cadastrar um registro de login de um usuario 
		Dado que o usuario seja cadastrado no sistema
		Quando eu realizar o login no sistema 
		Entao um registro da ação de login será cadastrado
		
	