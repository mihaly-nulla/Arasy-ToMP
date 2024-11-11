package br.ufv.caf.ModuloMissoes.componente;

/*
 * A classe ComponenteDadosQuest representa um componente para armazenar
 * dados relacionados a uma quest.
 * 
 * Inclui informações como o ID da quest, título e descrição.
 *
 * @author Thiago Cândido Rocha
 * 
 * @since 08/11/2023 - 09:23
 * 
 * @version 1.2
 */

public class ComponenteDadosQuest {

    /*
     * O identificador único para a quest.
     * 
     * @author Thiago Cândido Rocha
     * 
     * @since 08/11/2023 - 09:24
    */

    private String idQuest;

    /*
     * O título da quest.
     * 
     * @author Thiago Cândido Rocha
     * 
     * @since 08/11/2023 - 09:26
     * 
    */

    private String tituloQuest;

    /*
     * A descrição da quest.
     * 
     * @author Thiago Cândido Rocha
     * 
     * @since 08/11/2023 - 09:27
     */

    private String descricaoQuest;

    /*
     * Constrói uma nova instância de ComponenteDadosQuest com os detalhes
     * especificados da quest.
     * 
     * @author Thiago Cândido Rocha
     *
     * @param idQuest        O identificador único para a quest.
     * 
     * @param tituloQuest    O título do quest.
     * 
     * @param descricaoQuest A descrição da quest.
     * 
     * @since 08/11/2023 - 09:28
     */

    public ComponenteDadosQuest(String idQuest, String tituloQuest, String descricaoQuest) {
        this.idQuest = idQuest;
        this.tituloQuest = tituloQuest;
        this.descricaoQuest = descricaoQuest;
    }

    /*
     * Recupera o identificador único para a quest.
     * 
     * @author Thiago Cândido Rocha
     *
     * @return O ID da quest.
     * 
     * @since 08/11/2023 - 09:29
     */

    public String getIdQuest() {
        return idQuest;
    }

    /*
     * Recupera o título da quest.
     * 
     * @author Thiago Cândido Rocha
     *
     * @return O título da quest.
     * 
     * @since 08/11/2023 - 09:31
     */

    public String getTituloQuest() {
        return tituloQuest;
    }

    /*
     * Recupera a descrição da quest.
     *
     * @author Thiago Cândido Rocha
     * 
     * @return A descrição da quest
     * 
     * @since 08/11/2023 - 09:33
     */

    public String getDescricaoQuest() {
        return descricaoQuest;
    }
}
