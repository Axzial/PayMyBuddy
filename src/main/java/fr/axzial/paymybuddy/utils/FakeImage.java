package fr.axzial.paymybuddy.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FakeImage {
    String id;
    String author;
    int width;
    int height;
    String url;
    @JsonProperty("download_url")
    String downloadUrl;
}
