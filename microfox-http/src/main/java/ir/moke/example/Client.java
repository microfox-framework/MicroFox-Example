package ir.moke.example;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "client", description = "Example client object")
public class Client {
    private Long id;
    private String name;
    private String family;

    @Schema(description = "Client's id", example = "id", hidden = true)
    public Long getId() {
        return id;
    }

    @Schema(description = "Client's id", example = "id")
    public void setId(Long id) {
        this.id = id;
    }

    @Schema(description = "Client's last name", example = "Sheikh hosseini")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Schema(description = "Client's firs name", example = "Mahdi")
    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }
}
