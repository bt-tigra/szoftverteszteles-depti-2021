import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions

waiting {
    timeout = 10
    retryInterval = 1
    slow { timeout = 20 }
    reallyslow { timeout = 40 }
}

atCheckWaiting = true

environments {
    chrome {
        reportsDir = "target/test-reports_chrome"
        reportOnTestFailureOnly = true
        driver = {
            ChromeOptions options = new ChromeOptions()
            def prefs = [
                    "credentials_enable_service"        : false,
                    "password_manager_enabled"          : false,
                    "hardware_acceleration_mode.enabled": false,
                    "disable-accelerated-2d-canvas"     : true,
                    "disable-accelerated-video-decode"  : true,
            ]
            options.setExperimentalOption("prefs", prefs)
            options.addArguments("--enable-automation")
            options.addArguments("--no-sandbox")
            options.addArguments("--disable-infobars")
            options.addArguments("--disable-dev-shm-usage")
            options.addArguments("--disable-browser-side-navigation")
            options.addArguments("--disable-gpu")
            options.addArguments("--disable-background-networking")
            options.addArguments("--disable-default-apps")
            options.addArguments("--disable-extensions")
            options.addArguments("--disable-sync")
            options.addArguments("--disable-translate")
            options.addArguments("--no-first-run")
            options.addArguments("--safebrowsing-disable-auto-update")
            options.addArguments("--ignore-certificate-errors")
            options.addArguments("--ignore-ssl-errors")
            options.addArguments("--ignore-certificate-errors-spki-list")
            if (System.getProperty('headless').toBoolean()) {
                options.addArguments("--headless")
            }
            options.addArguments("--window-size=1920,1080")
            def driverInstance = new ChromeDriver(options)
            if (System.getProperty('maximized').toBoolean()) {
                driverInstance.manage().window().maximize()
            }
            driverInstance
        }
    }

    firefox {
        reportsDir = "target/test-reports_firefox"
        reportOnTestFailureOnly = true
        driver = {
            FirefoxOptions options = new FirefoxOptions()
            options.addPreference('layout.spellcheckDefault', 0)
            options.addPreference('services.sync.prefs.sync.layout.spellcheckDefault', false)
            options.addPreference('datareporting.healthreport.uploadEnabled', false)
            options.addPreference('datareporting.healthreport.service.firstRun', false)
            options.addPreference('privacy.trackingprotection.enabled', false)
            options.addPreference('security.insecure_field_warning.contextual.enabled', false)
            def driverInstance = new FirefoxDriver(options)
            driverInstance.manage().window().maximize()
            driverInstance
        }
    }
}