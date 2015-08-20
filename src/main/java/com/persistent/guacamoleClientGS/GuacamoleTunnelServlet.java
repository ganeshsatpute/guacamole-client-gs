package com.persistent.guacamoleClientGS;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.glyptodon.guacamole.GuacamoleException;
import org.glyptodon.guacamole.net.SimpleGuacamoleTunnel;
import org.glyptodon.guacamole.net.GuacamoleSocket;
import org.glyptodon.guacamole.net.GuacamoleTunnel;
import org.glyptodon.guacamole.net.InetGuacamoleSocket;
import org.glyptodon.guacamole.protocol.ConfiguredGuacamoleSocket;
import org.glyptodon.guacamole.protocol.GuacamoleConfiguration;
import org.glyptodon.guacamole.servlet.GuacamoleHTTPTunnelServlet;
import org.glyptodon.guacamole.servlet.GuacamoleSession;

public class GuacamoleTunnelServlet
        extends GuacamoleHTTPTunnelServlet {

    @Override
    protected GuacamoleTunnel doConnect(HttpServletRequest request)
            throws GuacamoleException {

        System.out.println("HOST NAME IS ----: " + request.getParameter("hostname") +
                "\t" + request.getParameter("hostname") +
                "\t" + request.getParameter("port") +
                "\t" + request.getParameter("vmconnect") +
                "\t" + request.getParameter("domain") +
                "\t" + request.getParameter("ignore-cert") +
                "\t" + request.getParameter("security") +
                "\t" + request.getParameter("username") +
                "\t" + request.getParameter("username"));



        // Create our configuration
        GuacamoleConfiguration config = new GuacamoleConfiguration();
        config.setProtocol("rdp");
        config.setParameter("hostname", request.getParameter("hostname"));
        config.setParameter("port", request.getParameter("port"));
        config.setParameter("vmconnect", request.getParameter("vmconnect"));
        config.setParameter("domain", request.getParameter("domain"));
        config.setParameter("ignore-cert", request.getParameter("ignore-cert"));
        config.setParameter("security", request.getParameter("security"));
        config.setParameter("username", request.getParameter("username"));
        config.setParameter("password", request.getParameter("password"));

        // Connect to guacd - everything is hard-coded here.
        GuacamoleSocket socket = new ConfiguredGuacamoleSocket(
                new InetGuacamoleSocket("localhost", 4822),
                config
        );

        // Establish the tunnel using the connected socket
        GuacamoleTunnel tunnel = new SimpleGuacamoleTunnel(socket);

        // Attach tunnel to session
        HttpSession httpSession = request.getSession(true);
        GuacamoleSession session = new GuacamoleSession(httpSession);
        session.attachTunnel(tunnel);

        // Return pre-attached tunnel
        return tunnel;

    }

}
