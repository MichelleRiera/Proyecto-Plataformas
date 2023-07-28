package servicios;

import java.util.List;

import DAO.usuarioDao;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import modelo.Usuario;
import negocio.usuarioN;

@Path("Usuarios")
public class usuarioS {
	
	@Inject
    private usuarioDao daoUsuario;

    @Inject
    private usuarioN negocioUsuario;

    @POST
    @Path("registrar")
    @Produces("application/json")
    public Response registrarUsuario(Usuario usuario) {
        try {
            negocioUsuario.agregarUsuarioConIdPersona(usuario);
            return Response.status(Response.Status.OK).entity(usuario).build();
        } catch (Exception e) {
            e.printStackTrace();
            Error error = new Error();
            error.setCodigo(99);
            error.setMensaje("Error al registrar el usuario: " + e.getMessage());
            return Response.status(Response.Status.OK).entity(error).build();
        }
    }

    @GET
    @Path("listar")
    @Produces("application/json")
    public Response listarUsuarios() {
        List<Usuario> usuarios = negocioUsuario.listarUsuarios();
        return Response.status(Response.Status.OK).entity(usuarios).build();
    }

    @DELETE
    @Path("eliminar")
    @Consumes("application/json")
    @Produces("application/json")
    public Response eliminarUsuario(Usuario usuario) {
        try {
            negocioUsuario.eliminarUsuarioPorNombreUsuario(usuario.getUsuario());
            Error respuesta = new Error();
            respuesta.setCodigo(1);
            respuesta.setMensaje("Usuario eliminado correctamente.");
            return Response.status(Response.Status.OK).entity(respuesta).build();
        } catch (Exception e) {
            e.printStackTrace();
            Error respuesta = new Error();
            respuesta.setCodigo(99);
            respuesta.setMensaje("Error al eliminar el usuario: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(respuesta).build();
        }
    }

    @PUT
    @Path("actualizar")
    @Consumes("application/json")
    @Produces("application/json")
    public Response actualizarUsuario(Usuario usuario) {
        try {
            negocioUsuario.actualizarUsuarioPorNombreUsuario(usuario);
            return Response.status(Response.Status.OK).entity("Usuario actualizado exitosamente.").build();
        } catch (Exception e) {
            e.printStackTrace();
            Error respuesta = new Error();
            respuesta.setCodigo(99);
            respuesta.setMensaje("Error al actualizar el usuario: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(respuesta).build();
        }
    }


    @POST
    @Path("login")
    @Produces("application/json")
    public Response loginUsuario(Usuario usuario) {
        try {
            Usuario usuarioLogueado = negocioUsuario.login(usuario.getUsuario(), usuario.getContrasenia());
            if (usuarioLogueado != null) {
                String mensajeBienvenida = "¡Bienvenido, " + usuarioLogueado.getUsuario() + "!";
                return Response.status(Response.Status.OK).entity(mensajeBienvenida).build();
            } else {
                Error respuesta = new Error();
                respuesta.setCodigo(99);
                respuesta.setMensaje("Credenciales de inicio de sesión inválidas.");
                return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Error respuesta = new Error();
            respuesta.setCodigo(99);
            respuesta.setMensaje("Error al iniciar sesión: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(respuesta).build();
        }
    }


}
