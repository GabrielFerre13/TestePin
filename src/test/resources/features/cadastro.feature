# language: pt
  Funcionalidade: Cadastro no site
    Como um usuario
    Gostaria de cadastrar site
    Para que eu possa ter mais ideias

  Cenário: Login valido
    Dado que estou acessando o site
    Quando eu informo usuario "fe.gabriel1313@gmail.com"
    E a senha "real1020"
    E seleciono entrar
    Entao deve ser redirecionado para a pagina inicial

    Cenário: Pesquisa por imagens
      Quando pesquisar por "carros"
      Entao deve ser exibida uma grade de imagens com resultados para "carros"

      Cenário: Salvar imagem
        Quando seleciono uma imagem
        E salvo em uma pasta
        Entao a imagem deve aparecer na pasta

      Cenário: Confirmacao no perfil
        Quando eu abrir o meu perfil
        Entao vejo se tem alguma imagem salva
