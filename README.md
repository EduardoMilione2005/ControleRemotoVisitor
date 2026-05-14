# Padrão Visitor com a ideia de Controle Remoto (Java)

## Objetivo

O padrão **Visitor** permite separar operações dos objetos sobre os quais elas atuam.

No exemplo abaixo:

* Os dispositivos (`TV`, `Som`, `ArCondicionado`) aceitam visitantes.
* O controle remoto funciona como um visitante.
* Cada visitante executa uma ação diferente em cada dispositivo.

---

# Estrutura do projeto

```text
src/
 ├── Dispositivo.java
 ├── TV.java
 ├── Som.java
 ├── ArCondicionado.java
 ├── Visitor.java
 ├── LigarVisitor.java
 ├── DesligarVisitor.java
 └── Main.java
```

---

# 1. Interface Element (Dispositivo)

```java
public interface Dispositivo {
    void aceitar(Visitor visitor);
}
```

Essa interface representa qualquer aparelho que pode receber comandos do controle remoto.

---

# 2. Classe TV

```java
public class TV implements Dispositivo {

    @Override
    public void aceitar(Visitor visitor) {
        visitor.visitar(this);
    }

    public void ligarTV() {
        System.out.println("TV ligada");
    }

    public void desligarTV() {
        System.out.println("TV desligada");
    }
}
```

---

# 3. Classe Som

```java
public class Som implements Dispositivo {

    @Override
    public void aceitar(Visitor visitor) {
        visitor.visitar(this);
    }

    public void ligarSom() {
        System.out.println("Som ligado");
    }

    public void desligarSom() {
        System.out.println("Som desligado");
    }
}
```

---

# 4. Classe ArCondicionado

```java
public class ArCondicionado implements Dispositivo {

    @Override
    public void aceitar(Visitor visitor) {
        visitor.visitar(this);
    }

    public void ligarAr() {
        System.out.println("Ar-condicionado ligado");
    }

    public void desligarAr() {
        System.out.println("Ar-condicionado desligado");
    }
}
```

---

# 5. Interface Visitor

```java
public interface Visitor {

    void visitar(TV tv);

    void visitar(Som som);

    void visitar(ArCondicionado ar);
}
```

Aqui definimos todas as operações possíveis para cada tipo de dispositivo.

---

# 6. Visitor para Ligar dispositivos

```java
public class LigarVisitor implements Visitor {

    @Override
    public void visitar(TV tv) {
        tv.ligarTV();
    }

    @Override
    public void visitar(Som som) {
        som.ligarSom();
    }

    @Override
    public void visitar(ArCondicionado ar) {
        ar.ligarAr();
    }
}
```

---

# 7. Visitor para Desligar dispositivos

```java
public class DesligarVisitor implements Visitor {

    @Override
    public void visitar(TV tv) {
        tv.desligarTV();
    }

    @Override
    public void visitar(Som som) {
        som.desligarSom();
    }

    @Override
    public void visitar(ArCondicionado ar) {
        ar.desligarAr();
    }
}
```

---

# 8. Classe Main

```java
public class Main {

    public static void main(String[] args) {

        Dispositivo tv = new TV();
        Dispositivo som = new Som();
        Dispositivo ar = new ArCondicionado();

        Visitor ligar = new LigarVisitor();
        Visitor desligar = new DesligarVisitor();

        System.out.println("=== LIGANDO DISPOSITIVOS ===");

        tv.aceitar(ligar);
        som.aceitar(ligar);
        ar.aceitar(ligar);

        System.out.println();

        System.out.println("=== DESLIGANDO DISPOSITIVOS ===");

        tv.aceitar(desligar);
        som.aceitar(desligar);
        ar.aceitar(desligar);
    }
}
```

---

# Saída esperada

```text
=== LIGANDO DISPOSITIVOS ===
TV ligada
Som ligado
Ar-condicionado ligado

=== DESLIGANDO DISPOSITIVOS ===
TV desligada
Som desligado
Ar-condicionado desligado
```

---

# Como o Visitor funciona aqui

## Dispositivos

Os aparelhos apenas aceitam visitantes:

```java
visitor.visitar(this);
```

Eles não sabem qual operação será feita.

---

## Visitors

Os visitantes carregam o comportamento:

* `LigarVisitor` → liga tudo
* `DesligarVisitor` → desliga tudo

Isso permite adicionar novos comportamentos sem alterar as classes dos dispositivos.

---

# Vantagens do Visitor

* Separa comportamento da estrutura.
* Facilita adicionar novas operações.
* Evita muitos `if` ou `instanceof`.
* Ótimo para sistemas com muitos tipos de objetos.

---

# Quando usar

Use Visitor quando:

* Existem vários tipos de objetos.
* Você precisa adicionar operações frequentemente.
* Quer evitar alterar as classes principais.

Exemplos:

* Controle remoto universal
* Sistemas bancários
* Compiladores
* Árvores de arquivos
* Relatórios
* Jogos

---

# Casos de Teste

## Objetivo dos testes

Os testes verificam se:

* Os dispositivos aceitam visitantes corretamente.
* O visitor de ligar executa a ação esperada.
* O visitor de desligar executa a ação esperada.
* O comportamento muda conforme o visitor utilizado.

---

# Dependência Maven (JUnit 5)

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.2</version>
    <scope>test</scope>
</dependency>
```

---

# Estrutura de testes

```text
src/test/java/
 └── VisitorTest.java
```

---

# Classe VisitorTest

```java
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class VisitorTest {

    @Test
    void deveLigarTV() {

        TV tv = new TV();
        Visitor ligar = new LigarVisitor();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        tv.aceitar(ligar);

        assertTrue(output.toString().contains("TV ligada"));
    }

    @Test
    void deveDesligarTV() {

        TV tv = new TV();
        Visitor desligar = new DesligarVisitor();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        tv.aceitar(desligar);

        assertTrue(output.toString().contains("TV desligada"));
    }

    @Test
    void deveLigarSom() {

        Som som = new Som();
        Visitor ligar = new LigarVisitor();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        som.aceitar(ligar);

        assertTrue(output.toString().contains("Som ligado"));
    }

    @Test
    void deveDesligarArCondicionado() {

        ArCondicionado ar = new ArCondicionado();
        Visitor desligar = new DesligarVisitor();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        ar.aceitar(desligar);

        assertTrue(output.toString().contains("Ar-condicionado desligado"));
    }
}
```

---

# O que os testes fazem

## Captura da saída

```java
ByteArrayOutputStream output = new ByteArrayOutputStream();
System.setOut(new PrintStream(output));
```

Captura o que foi exibido no console.

---

## Execução do Visitor

```java
tv.aceitar(ligar);
```

O dispositivo recebe o visitor.

---

## Validação

```java
assertTrue(output.toString().contains("TV ligada"));
```

Verifica se a mensagem correta foi executada.

---

# Resultado esperado dos testes

```text
Tests run: 4
Tests successful: 4
Tests failed: 0
```

---

# Conclusão

O padrão Visitor foi aplicado utilizando a ideia de um controle remoto universal.

Os dispositivos aceitam visitantes diferentes, permitindo adicionar novos comportamentos sem modificar as classes principais.

Os testes unitários garantem que cada visitor execute corretamente sua operação em cada dispositivo.

---

# README.md

````md
# Controle Remoto Universal - Padrão Visitor

Projeto desenvolvido em Java utilizando o padrão de projeto Visitor.

O sistema simula um controle remoto universal capaz de executar operações em diferentes dispositivos eletrônicos.

---

# Objetivo

Demonstrar o funcionamento do padrão Visitor utilizando:

- TV
- Som
- Ar-condicionado

O controle remoto atua como Visitor, executando operações diferentes em cada dispositivo.

---

# Tecnologias utilizadas

- Java
- Maven
- JUnit 5

---

# Estrutura do projeto

```text
ControleRemotoVisitor/
│
├── pom.xml
│
├── src/
│   ├── main/java/
│   │   ├── Dispositivo.java
│   │   ├── Visitor.java
│   │   ├── TV.java
│   │   ├── Som.java
│   │   ├── ArCondicionado.java
│   │   ├── LigarVisitor.java
│   │   ├── DesligarVisitor.java
│   │   └── Main.java
│   │
│   └── test/java/
│       └── VisitorTest.java
│
└── README.md
````

---

# Como executar

## 1. Clonar o projeto

```bash
git clone <url-do-projeto>
```

---

## 2. Abrir no IntelliJ ou Eclipse

Importe o projeto Maven.

---

## 3. Executar a classe Main

```java
Main.java
```

---

# Saída esperada

```text
=== LIGANDO DISPOSITIVOS ===
TV ligada
Som ligado
Ar-condicionado ligado

=== DESLIGANDO DISPOSITIVOS ===
TV desligada
Som desligado
Ar-condicionado desligado
```

---

# Executando os testes

Execute:

```bash
mvn test
```

Ou rode a classe:

```text
VisitorTest.java
```

---

# Explicação do padrão Visitor

O padrão Visitor separa os comportamentos das estruturas dos objetos.

Neste projeto:

* Os dispositivos recebem visitantes.
* Os visitantes executam operações.
* Novos comportamentos podem ser adicionados sem alterar os dispositivos.

---

# Visitors implementados

## LigarVisitor

Responsável por ligar todos os dispositivos.

## DesligarVisitor

Responsável por desligar todos os dispositivos.

---

# Benefícios do padrão

* Separação de responsabilidades
* Facilidade de manutenção
* Facilidade para adicionar novas operações
* Código mais organizado

---

# Autor

Projeto acadêmico demonstrando o padrão Visitor em Java.

```
```
