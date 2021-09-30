package K360.Service.Hash;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Hasher {

    public static String calcHmacSha256(String key, String data) {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);

        byte[] hmacSha256;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "HmacSHA256");
            mac.init(secretKeySpec);
            hmacSha256 = mac.doFinal(dataBytes);
        } catch (Exception e) {
            throw new RuntimeException("Failed to calculate hmac-sha256", e);
        }

        return Base64.getEncoder().encodeToString(hmacSha256);
    }

}
