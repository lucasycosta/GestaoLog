#language:pt
Funcionalidade: Cadastro de registro 
	
	Cenario: Não cadastrar registro com idUsuario vazio
		Dado que um usuario cadastrado no sistema 
		Quando executar uma ação no sistema 
		Mas não captura seu id
		Entao o sistema não registra a ação sem o id
		
	Cenario: Não cadastrar registro com nomeUsuario vazio
		Dado que um usuario cadastrado no sistema 
		Quando executar uma ação no sistema 
		Mas não captura seu nome de usuario
		Entao o sistema não registra a ação sem o nome
		
	Cenario: Não cadastrar registro como nomeUsuario invalido
		Dado que um usuario cadastrado no sistema 
		Quando executar uma ação no sistema 
		Mas seu nome começa com letra minuscula 
		Entao o sistema não registra a ação com nome invalido
		
	Cenario: Não cadastrar registro com email invalido
		Dado que um usuario cadastrado no sistema 
		Quando executar uma ação no sistema 
		Mas seu email não segue o padrão
		Entao o sistema não registra a ação com email invalido
		
	Cenario: Não cadastrar registro com email vazio
		Dado que um usuario cadastrado no sistema 
		Quando executar uma ação no sistema 
		Mas não captura seu email
		Entao o sistema não registra a ação com email vazio
		
	Cenario: Não cadastrar registro com nivelAcesso vazio
		Dado que um usuario cadastrado no sistema 
		Quando executar uma ação no sistema 
		Mas não captura seu nivel de acesso
		Entao o sistema não registra a ação sem nivel de acesso
		
	Cenario: Não cadastrar registro com funcionalidade vazio
		Dado que um usuario cadastrado no sistema 
		Quando executar uma ação no sistema 
		Mas não captura a funcionalidade utilizada
		Entao o sistema não registra a ação com funcionalidade vazio
	
	Cenario: Cadastrar um registro de ação de um usuario 
		Dado que o usuario seja cadastrado no sistema
		Quando realizar uma acao no sistema 
		Entao um registro dessa acao será realizado
		
	