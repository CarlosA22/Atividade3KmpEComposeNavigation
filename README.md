Durante o desenvolvimento desta atividade, qual
diferença você percebeu entre o modelo tradicional de Multi-Activity
(Android Nativo) e o modelo de Single Activity aplicado ao contexto
Multiplataforma? Como isso impacta o reaproveitamento de código entre
Android e iOS?

No Android nativo clássico, com Multi Activity, a gente acaba amarrando cada tela a um componente pesado do próprio sistema operacional. Cada transição exige lidar com Intents e com aquele ciclo de vida complexo. Já no modelo Single Activity com o Compose, a dinâmica muda completamente. A MainActivity virou só uma "casca", um ponto de partida. Dali pra frente, toda a troca de telas acontece de forma muito mais fluida, direto na camada visual com as funções Composable, sem precisar ficar chamando o sistema Android a cada clique.

O impacto disso no reaproveitamento de código é justamente o que faz o KMP ser bom. Se a navegação continuasse dependendo de Activities, eu não conseguiria levar isso pro iOS. Como eu abstraí isso no modelo Single Activity, deu para usar o Android e o iOS apenas como "hospedeiros" (com uma Activity no Android e um UIViewController no iOS). Na prática, eu consegui jogar toda a lógica de rotas e a interface visual para o código compartilhado. Em vez de escrever a navegação em dobro para cada sistema, eu criei o fluxo uma vez só, economizando muito tempo e garantindo que o app se comporte do mesmo jeito nas duas plataformas.
