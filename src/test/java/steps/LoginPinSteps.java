package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginPinSteps {

    public static WebDriver driver;

    @Before
    public void abrirNavegador() {
        WebDriverManager.chromedriver().browserVersion("140.0.7339.128").setup();
        driver = new ChromeDriver();
        System.out.println("Abrindo");
    }


    @Dado("que estou acessando o site")
    public void que_estou_acessando_o_site() {
        driver.get("https://br.pinterest.com/");

    }
    @Quando("eu informo usuario {string}")
    public void euInformoUsuario(String usuario) {
        driver.findElement(By.id("email")).sendKeys(usuario);
    }


    @E("a senha {string}")
    public void aSenha(String senha) {
        driver.findElement(By.id("password")).sendKeys(senha);
    }

    @E("seleciono entrar")
    public void selecionoEntrar() {
        driver.findElement(By.tagName("button")).click();
    }

    @Entao("deve ser redirecionado para a pagina inicial")
    public void deve_ser_redirecionado_para_a_pagina_inicial() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("https://br.pinterest.com/"));
        String url = driver.getCurrentUrl();
        assertEquals("https://br.pinterest.com/", url);
    }


    @Quando("pesquisar por {string}")
    public void pesquisarPor(String carro) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement barraPesquisa = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("searchBoxInput")));
        barraPesquisa.sendKeys(carro);
        barraPesquisa.submit();
    }

    @Entao("deve ser exibida uma grade de imagens com resultados para {string}")
    public void deveSerExibidaUmaGradeDeImagensComResultadosPara(String carro) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement imagem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("img")));
        assertTrue(imagem.isDisplayed());

    }

    @Quando("seleciono uma imagem")
    public void selecionoUmaImagem() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement primeiraImagem = wait.until(ExpectedConditions.elementToBeClickable(By.tagName("img")));
            primeiraImagem.click();
    }

    @E("salvo em uma pasta")
    public void salvoEmUmaPasta() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement botaoSalvar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[aria-label='Salvar']")));
        botaoSalvar.click();

    }

    @Entao("a imagem deve aparecer na pasta")
    public void aImagemDeveAparecerNaPasta() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement confirmacao = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[aria-label= 'salvar']")));
        confirmacao.click();
    }

    @Quando("eu abrir o meu perfil")
    public void euAbrirOMeuPerfil() {
        driver.get("https://br.pinterest.com/fegabriel1313/_pins/");

    }

    @Entao("vejo se tem alguma imagem salva")
    public void vejoSeTemAlgumaImagemSalva() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement imagem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("img")));
        assertTrue(imagem.isDisplayed());
    }
    @After
    public void fecharBrower(){
        driver.quit();

        System.out.println("Terminando");
    }
}
