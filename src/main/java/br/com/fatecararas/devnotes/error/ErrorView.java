package br.com.fatecararas.devnotes.error;

import java.util.Map;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class ErrorView implements ErrorViewResolver {

    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        ModelAndView modelAndView = new ModelAndView("/error");
        modelAndView.addObject("status", status.value());

        switch (status.value()) {
            case 404:
                modelAndView.addObject("error", "Página não encontrada");
                modelAndView.addObject("message",
                        String.format("A página solicitada '%s' não existe", model.get("path")));
                break;
            case 500:
                modelAndView.addObject("error", "Ocorreu um erro interno no servidor.");
                modelAndView.addObject("message", "Ocorreu um erro interno inesperado, tente mais tarde.");
                break;

            default:
                modelAndView.addObject("error", model.get("error"));
                modelAndView.addObject("message", model.get("message"));
                break;
        }
        return modelAndView;
    }

}
