package az.edu.aztu.secretmanagement;

import org.springframework.context.annotation.Configuration;
import org.springframework.vault.core.VaultKeyValueOperationsSupport;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;

@Configuration
public class DatabaseConnection {

    private String url;
    private String username;
    private String password;

    private VaultTemplate vaultTemplate;

    public DatabaseConnection (VaultTemplate vaultTemplate) {
        VaultResponse response = vaultTemplate
                .opsForKeyValue("secret", VaultKeyValueOperationsSupport.KeyValueBackend.KV_2).get("database");

        this.url = (String) response.getData().get("url");
        this.username = (String) response.getData().get("username");
        this.password = (String) response.getData().get("password");
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
