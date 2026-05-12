# Instruções para rodar Bai1.kt no Android Studio

## Método 1: Usando a configuração de execução (Recomendado)
1. Abra o Android Studio
2. Na barra superior, localize o dropdown de configurações de execução (ao lado do botão Run)
3. Selecione "Bai1" (ou Bai2, Bai3, Bai4, Bai5)
4. Clique no botão Run (Shift+F10) ou aperte o ícone de play verde

## Método 2: Pela terminal
```bash
./gradlew :console:bai1    # Para Bai1
./gradlew :console:bai2    # Para Bai2
./gradlew :console:bai3    # Para Bai3
./gradlew :console:bai4    # Para Bai4
./gradlew :console:bai5    # Para Bai5
```

## Método 3: Clique direito no arquivo
1. Abra `console/src/main/kotlin/com/example/lab1/Bai1.kt`
2. Clique direito no arquivo
3. Selecione "Run 'Bai1Kt'"

## Notas
- Os arquivos Bai*.kt estão no módulo `console`, não no módulo `app`
- O módulo `app` é para Android (não tem sourceSets)
- O módulo `console` é para aplicações JVM (tem sourceSets)

