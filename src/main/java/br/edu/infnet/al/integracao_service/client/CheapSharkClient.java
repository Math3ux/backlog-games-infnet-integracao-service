package br.edu.infnet.al.integracao_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@FeignClient(name = "cheapshark", url = "${cheapshark.api.url}", configuration = FeignConfig.class)
public interface CheapSharkClient {

    @GetMapping("/games")
    List<JogoExternoDTO> buscarJogosPorTitulo(@RequestParam("title") String titulo);
}