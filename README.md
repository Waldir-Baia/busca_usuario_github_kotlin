# busca_usuario_github_kotlin

# Ideia inicial deste projeto e criar um app que busque por usuários no github.

# 1° Arquitetura usada MVVM. O motivo de utilizar esta arquitetura e para poder separar as responsabilidades de cada camada e não deixar a view reponsavel por tudo. 

# 2° Requisições HTTP. A ideia e utilizar o Retrofit para realizar as chamadas a minha API. 

# 3° Utilização do Hilt, para realizar a injeção de dependencia no codigo. Controlando a instancia de cada classe. 

# 4° Utilizando o Diff para otimizar minha recycler. 

# Este aplicativo foi desenvolvido para realizar buscas de usuários no GitHub. A tela principal, chamada "Buscar Usuários", será a primeira exibida ao abrir o app. Nela, há três botões localizados na parte inferior da tela: Buscar, Filtro e Histórico.

# Cada botão representa uma funcionalidade específica do aplicativo:

# Buscar: Ao clicar, uma caixa de pesquisa é exibida. O usuário pode digitar um termo e, ao confirmar a busca, o aplicativo realizará a pesquisa no GitHub com base no filtro selecionado. Por padrão, a busca é feita por usuário.

# Filtro: Abre um dialog que permite ao usuário escolher o tipo de filtro a ser aplicado na busca. Foram implementadas as opções de filtro por Usuário, Linguagem e Localização.

# Histórico: Abre uma tela que exibe todas as contas de usuários do GitHub que foram acessadas anteriormente no aplicativo.. 

# Uma das melhorias que podem ser implementadas futuramente no aplicativo está relacionada aos métodos de importação de dados. Atualmente, esses métodos estão espalhados, mas podemos refatorá-los criando uma única classe responsável por centralizar todas as importações utilizadas no app.
# Como estamos utilizando o Hilt para injeção de dependência, essa classe poderá ser instanciada de forma simples e eficiente, facilitando sua utilização nas ViewModels.
# Essa abordagem traz diversos benefícios:
# Organização: A lógica de importação ficará centralizada, tornando o código mais coeso e fácil de manter.
# Reutilização: A classe poderá ser reutilizada em diferentes partes do aplicativo, evitando duplicação de código.
# Limpeza da ViewModel: A ViewModel ficará mais enxuta e focada apenas na lógica de apresentação, delegando responsabilidades de importação à nova classe.
# Outra melhoria que pode ser bastante útil é separar as responsabilidades de cada Fragment com sua própria ViewModel, evitando concentrar toda a lógica em uma MainViewModel.
# No cenário atual deste aplicativo, foi criada uma ViewModel específica para o HistoryFragment. A ideia por trás dessa separação é garantir que o Fragment tenha controle total sobre os fluxos e dados que são relevantes apenas para ele, sem poluir a ViewModel principal com responsabilidades que não dizem respeito às demais telas. 
# Nos metodos de importações, podemos criar uma paginação para retornar todos os dados do github, da forma que está hoje, esta limitando o tamanho de registros que estou recebendo.
# Layout e muito importante melhorar ele. 

# Uma funcionalidade que seria muito interessante de ser desenvolvida futuramente é permitir que, ao buscar por um repositório, o usuário possa realizar o download daquele repositório, desde que ele seja público.
# Essa funcionalidade agregaria valor ao aplicativo, permitindo que o usuário:
# Tenha acesso offline ao conteúdo do repositório;
# Analise os arquivos e estrutura do projeto diretamente no dispositivo;
# Compartilhe o projeto com outras pessoas ou ferramentas de análise de código.
# A implementação poderia ser feita utilizando a própria API do GitHub, que fornece o link de download no formato .zip para repositórios públicos. Com isso, o aplicativo faria o download, salvaria localmente e, se desejado, poderia até oferecer a opção de descompactar ou visualizar o conteúdo diretamente.
# Essa adição tornaria o app não apenas uma ferramenta de busca, mas também uma plataforma de acesso e exploração prática de repositórios.


# No mais, meu muito obrigado por avaliar meu aplicativo.