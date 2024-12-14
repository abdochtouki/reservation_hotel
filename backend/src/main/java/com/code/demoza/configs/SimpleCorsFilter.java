package com.code.demoza.configs;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;


        // Récupère l'origine de la requête à partir de l'en-tête "Origin"
        String originHeader = request.getHeader("Origin");

        // Ajoute les en-têtes CORS (Cross-Origin Resource Sharing) à la réponse
        response.setHeader("Access-Control-Allow-Origin", originHeader); // Autorise toutes les origines spécifiées dans l'en-tête "Origin"
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE"); // Autorise les méthodes HTTP spécifiées
        response.setHeader("Access-Control-Max-Age", "3600"); // Définit la durée de validité en secondes des informations de prévol
        response.setHeader("Access-Control-Allow-Headers", "*"); // Autorise tous les en-têtes

        // Si la méthode HTTP est OPTIONS (utilisée pour les requêtes prévol)
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
           response.setStatus(HttpServletResponse.SC_OK); // Ne fait rien (la réponse est déjà configurée avec les en-têtes CORS)
        } else {
            // Sinon, c'est une requête normale
            filterChain.doFilter(servletRequest, servletResponse); // Passe la requête au filtre suivant dans la chaîne
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
