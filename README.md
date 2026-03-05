<div style="display: flex;flex-direction: row">
  <img widt="48" height="48" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/android/android-original.svg" />
  <img widt="48" height="48" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/kotlin/kotlin-original.svg" />
  <img widt="48" height="48" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/jetpackcompose/jetpackcompose-original.svg" />
  <img widt="48" height="48" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/ktor/ktor-original.svg" />
</div>

# Inspiração
O App foi desenvolvido para estudar e praticar o desenvolvimento Android com kotlin. O design foi inspirado no desafio [Explorador de filmes](https://www.figma.com/community/file/1509971053495906327/explorador-de-filmes) da **_rocketseat_**, com algumas mudanças no layout.

# Overview
## Tela inicial

<img width="430" height="887" alt="Image" src="https://github.com/user-attachments/assets/dc8a098a-0923-4911-a277-a0bc1a4c8b59" />

Lista os títulos populares do dia realizando uma chamada para [/movie/popular](https://developer.themoviedb.org/reference/movie-popular-list), cada título é apresentado em um card exibindo o poster, o nome, a avaliação e o ano de lançamento.
Após a requisição, os filmes são armazenados no [room](https://developer.android.com/jetpack/androidx/releases/room) com `stale time` de 12 horas

## Detalhes do filme

<img width="430" height="887" alt="Image" src="https://github.com/user-attachments/assets/d7735f59-f9d5-4510-a8ee-1a182d8becd3" />

Para a tela de detalhes do filme foi utlizado apenas `datasource` remoto, sendo o endpoint [/movie/{movie_id}](https://developer.themoviedb.org/reference/movie-details) para obter os detalhes do filme e [/movie/{movie_id}/videos](https://developer.themoviedb.org/reference/movie-videos) para buscar a chave do trailer.
Nessa tela é apresentado os detalhes do filme selecionado, exibindo a imagem de plano de fundo, título, duração, data de lançamento, avaliação, categorias, sinópse e os botões de favoritar e assistir ao trailer.

Ao selecionar o botão _Assistir trailer_ o usuário é redirecionado para o vídeo do trailer no YouTube.

## Pesquisa

<img width="430" height="887" alt="Image" src="https://github.com/user-attachments/assets/573fb00b-da54-420f-a93d-ae826db88822" />

A tela de pesquisa utiliza um debounce para realizar requisições no endpoint [/search/movie](https://developer.themoviedb.org/reference/search-movie), realizando a busca de filmes enquanto o usuário digita.

A busca inicia após o usuário digitar o mínimo de 3 caracteres e a lista volta ao estado de vazio quando o usuário limpa o campo.

## Favoritos

<img width="430" height="887" alt="Image" src="https://github.com/user-attachments/assets/b9a42db8-d5aa-4b55-9465-cfe95e0eaf51" />

<img width="430" height="887" alt="Image" src="https://github.com/user-attachments/assets/ba14e560-4152-4f1e-9ec9-476ced418242" />

Nessa tela, são exibidos os filmes que o usuário adicionou a lista de favoritos. o usuário pode remover o título da lista pressionando o botão deletar, exibido no canto superior direito do card, e a lista é atualizada automaticamente.

# Dependências

- [navigation-compose](https://developer.android.com/jetpack/androidx/releases/navigation?hl=pt-br)
- [room](https://developer.android.com/jetpack/androidx/releases/room)
- [paging](https://developer.android.com/jetpack/androidx/releases/paging?hl=pt-br)
- [serialization](https://kotlinlang.org/docs/serialization.html)
- [ktor](https://ktor.io/docs/welcome.html)
- [coil](https://coil-kt.github.io/coil/getting_started/)
- [koin](https://insert-koin.io/docs/quickstart/android/)
