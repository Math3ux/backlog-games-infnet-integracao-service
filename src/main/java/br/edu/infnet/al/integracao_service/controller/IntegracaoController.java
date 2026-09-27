package br.edu.infnet.al.integracao_service.controller;

import br.edu.infnet.al.integracao_service.client.CheapSharkClient;
import br.edu.infnet.al.integracao_service.client.JogoExternoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/externa/jogos")
@Tag(name = "Integração Externa", description = "Microsserviço Gateway para consulta de catálogo em APIs de terceiros")
public class IntegracaoController {

    private final CheapSharkClient cheapSharkClient;

    public IntegracaoController(CheapSharkClient cheapSharkClient) {
        this.cheapSharkClient = cheapSharkClient;
    }

    @GetMapping
    @Operation(summary = "Buscar jogos por título", description = "Consulta a API externa da CheapShark e retorna a lista de jogos encontrados formatada no padrão interno.")
    public ResponseEntity<List<JogoExternoDTO>> buscarJogosNaCheapShark(@RequestParam String titulo) {
        // O serviço delega a chamada diretamente ao FeignClient
        List<JogoExternoDTO> resultados = cheapSharkClient.buscarJogosPorTitulo(titulo);

        if (resultados == null || resultados.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(resultados);
    }
}