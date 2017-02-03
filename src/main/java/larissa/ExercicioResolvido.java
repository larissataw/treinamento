package larissa;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.BuscaConfig;
import main.Exercicio;
import main.PesquisaTermo;

public class ExercicioResolvido implements Exercicio {


	public static void main(String[] args) {
		ExercicioResolvido ex = new ExercicioResolvido();
//		long result = ex.getNumeroAproximadoDoResultadoDaBuscaPor("teste");
		List<String> resultUrls = ex.getUrls("teste");
//		List<String> resultPag = ex.getUrls("teste", 5);
//		List<String> resultConfig = ex.getUrls(config);
//		String resultWiki = ex.getWikiResume("john lennon");

		System.out.println(resultUrls);

	}
	
	public long getNumeroAproximadoDoResultadoDaBuscaPor(String termo) {

		
		long result = 0;	
		PesquisaTermo pesquisa = new PesquisaTermo();
		pesquisa.pesquisaTermo(termo);
		
		try {

			pesquisa.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("resultStats")));
					
			String resultString = pesquisa.getWebdriver().findElement(By.id("resultStats")).getText();
			Pattern pattern = Pattern.compile("(\\d+\\.?)+");
			Matcher matcher = pattern.matcher(resultString);
			matcher.find();
			String justNumber = matcher.group();

			NumberFormat nf = NumberFormat.getInstance();
			try {
				Number number = nf.parse(justNumber);
				result = number.longValue();
			} catch (ParseException e) {
				e.printStackTrace();
			}

		} catch (Throwable t) {

			t.printStackTrace();

		} finally {
			pesquisa.getWebdriver().close();
			pesquisa.getWebdriver().quit();
		}

		return result;
	}

	public List<String> getUrls(String termo) {

		List<String> urls = new ArrayList<String>();

		PesquisaTermo pesquisa = new PesquisaTermo();
		pesquisa.pesquisaTermo(termo);
		
		try {
			
			pesquisa.getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div/h3/a")));
			List<WebElement> links = pesquisa.getWebdriver().findElements(By.xpath("//div/h3/a"));

			for (WebElement element : links) {

				urls.add(element.getAttribute("href"));
			}

		} catch (Throwable t) {

			t.printStackTrace();

		} finally {
			pesquisa.getWebdriver().close();
			pesquisa.getWebdriver().quit();
		}

		return urls;
	}

	public List<String> getUrls(String termo, int pag) {

		List<String> urls = new ArrayList<String>();
		int pagina = 0;

		PesquisaTermo pesquisa = new PesquisaTermo();
		pesquisa.pesquisaTermo(termo);
		
		try {

			List<WebElement> idPags = pesquisa.getWebdriver().findElements(By.xpath("//span[contains(@class, 'csb ch')]/.."));
	

			for (WebElement element : idPags) {

				if (!element.getText().equals("Mais") && !element.getText().equals("Anterior")) {
					pagina = Integer.parseInt(element.getText());

					if (pagina == pag) {
						pesquisa.getWait().until(ExpectedConditions.elementToBeClickable(element));
						element.click();
					}
				}
			}

			pesquisa.getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div/h3/a")));
			List<WebElement> links = pesquisa.getWebdriver().findElements(By.xpath("//div/h3/a"));

			for (WebElement link : links) {

				urls.add(link.getAttribute("href"));
			}
			
			
		} catch (Throwable t) {

			t.printStackTrace();

		} finally {
			pesquisa.getWebdriver().close();
			pesquisa.getWebdriver().quit();
		}

		return urls;
	}

	public List<String> getUrls(BuscaConfig config) {

		
		
		return null;
	}

	public String getWikiResume(String termo) {

		String resultWiki = null;

		PesquisaTermo pesquisa = new PesquisaTermo();
		pesquisa.pesquisaTermo(termo);
		
		try {
			
			pesquisa.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'kno-rdesc')]")));
			resultWiki = pesquisa.getWebdriver().findElement(By.xpath("//div[contains(@class, 'kno-rdesc')]")).getText();

		} catch (Throwable t) {

			t.printStackTrace();

		} finally {
			pesquisa.getWebdriver().close();
			pesquisa.getWebdriver().quit();
		}
		
		return resultWiki;
	}

}

