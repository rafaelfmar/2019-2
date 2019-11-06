package com.example.app.apresentacao;

import com.example.app.negocio.ClienteNegocio;
import com.example.app.negocio.dominio.ClienteDTO;
import com.example.app.negocio.excecao.ObjetoJaExisteException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteNegocio clienteNegocio;

    @GetMapping("/cliente")
    public String listar(Model memoria) {

        memoria.addAttribute("listaClientes",
                ClienteDTO.ModelsFromDTOs(clienteNegocio.listar()));

        return "cliente-view";
    }

    @PostMapping("/cliente/criar")
    public String criar(ClienteModel cliente) {

        try {
            clienteNegocio.incluir(ClienteDTO.DTOFromModel(cliente));

        } catch (ObjetoJaExisteException ex) {
            Logger.getLogger(PaisController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "redirect:/cliente";
    }
}
