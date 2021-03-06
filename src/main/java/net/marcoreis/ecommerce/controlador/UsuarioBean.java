package net.marcoreis.ecommerce.controlador;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;

import net.marcoreis.ecommerce.entidades.Usuario;
import net.marcoreis.ecommerce.util.JPAUtil;

@RequestScoped
@ManagedBean
public class UsuarioBean extends BaseBean {
    private static final long serialVersionUID = -2658024901938874346L;
    private Usuario usuario;
    private Collection<Usuario> usuarios;

    @PostConstruct
    public void init() {
        carregarUsuarios();
        usuario = new Usuario();
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void salvar() {
        try {
            EntityManager em = JPAUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            em.persist(getUsuario());
            em.getTransaction().commit();
            em.close();
            infoMsg("Dados gravados com sucesso");
        } catch (Exception e) {
            errorMsg(e);
        }
    }

    public void carregarUsuarios() {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        usuarios = em.createQuery("from Usuario").getResultList();
        em.close();
    }

    public Collection<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Collection<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
