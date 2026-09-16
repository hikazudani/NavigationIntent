# NavigationIntent

Aplicativo Android de estudo, escrito em Kotlin com Jetpack Compose. Ele existe para praticar duas coisas que costumam andar juntas: navegar entre telas usando o Navigation Compose e compartilhar estado entre destinos diferentes do mesmo grafo.

## O que o aplicativo faz

A tela inicial é a `IntentScreen`, que mostra apenas o parâmetro guardado no momento. No topo fica uma barra com um menu de três pontos, e a única opção dele, "Set parameter", leva para a `ParameterScreen`. Lá existe um campo de texto e o botão "Save and quit", que grava o valor digitado e volta para a tela anterior. Ao voltar, a `IntentScreen` já exibe o texto novo.

O menu aparece somente enquanto a `IntentScreen` está em primeiro plano. A `MainActivity` observa a entrada atual da pilha de navegação e repassa essa informação para a barra superior, que esconde as ações quando a rota visível é outra.

## Como o código está organizado

```
app/src/main/java/br/edu/ifsp/scl/sc3038432/navigationintent/
├── MainActivity.kt              Scaffold, barra superior e criação do NavHostController
├── MainViewModel.kt             estado compartilhado entre as telas
├── navigation/
│   ├── Screen.kt                rotas do grafo
│   └── MainNavHost.kt           grafo de navegação e ligação das telas com o ViewModel
└── ui/composable/
    ├── component/               MainTopAppBar e MainDropDownMenu
    └── screen/                  IntentScreen e ParameterScreen
```

## Decisões que valem a pena notar

As rotas moram em uma sealed class chamada `Screen`, então nenhum arquivo precisa repetir a string da rota solta no meio do código.

As telas não conhecem o `NavHostController`. Elas recebem funções como `onSave` e `onQuit` e deixam para o `MainNavHost` a decisão do que acontece depois, o que mantém cada composable simples de testar, de visualizar no preview e de reaproveitar em outro contexto. Essa separação foi feita aos poucos, e o histórico de commits mostra a versão acoplada antes da versão atual.

O valor digitado fica no `MainViewModel`, exposto como `StateFlow` e coletado no `MainNavHost` com `collectAsStateWithLifecycle`. Como o ViewModel tem escopo de activity, ir para a segunda tela e voltar não perde o que foi salvo.

Todos os composables têm preview em tema claro e escuro. O `Surface` dentro dos previews está lá só para dar cor de fundo, porque sem ele o tema escuro fica transparente e não dá para enxergar nada.

## Rodando o projeto

A forma mais direta é abrir a pasta no Android Studio e usar o botão de executar, com um emulador ou aparelho conectado.