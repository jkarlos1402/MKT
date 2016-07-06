package com.femsa.mkt.filters;

import com.femsa.mkt.pojos.MktAdmin_Usuario;
import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TMXIDSJPINAM
 */
@WebFilter(filterName = "AccessFilter", urlPatterns = {"/faces/Login.xhtml", "/faces/menu.xhtml"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ERROR, DispatcherType.INCLUDE})
public class AccessFilter implements Filter {  

    /**
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String url = req.getRequestURL().toString();
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        MktAdmin_Usuario usuario = null;
        if (session != null) {
            usuario = (MktAdmin_Usuario) session.getAttribute("session_user");
        }
        if (usuario != null && usuario.getPkUsuario() != null && !url.contains("menu.xhtml")) {
            RequestDispatcher rd = request.getRequestDispatcher("menu.xhtml");
            rd.forward(request, response);
        } else if (usuario == null && url.contains("menu.xhtml") && "get".equalsIgnoreCase(req.getMethod())) {
            res.sendError(404);
        } else {
            chain.doFilter(request, response);
        }
    }

    /**
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // comentario
    }

    /**
     *
     */
    @Override
    public void destroy() {
         // comentario
    }

}
