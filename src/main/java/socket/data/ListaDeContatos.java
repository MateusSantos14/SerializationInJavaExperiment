package socket.data;


import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaDeContatos implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement
    private Pessoa dono;
    @XmlElement
    private ArrayList<Pessoa> contatos;


    public ListaDeContatos(){

    }
    public ListaDeContatos(Pessoa dono) {
        this.dono = dono;
        this.contatos = new ArrayList<>();
    }

    public void adicionarContato(Pessoa contato) {
        contatos.add(contato);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(dono.toString()).append("|");
        for (int i = 0; i < contatos.size(); i++) {
            sb.append(contatos.get(i).toString());
            if (i < contatos.size() - 1) {
                sb.append(" | ");
            }
        }
        return sb.toString();
    }


    public String toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dono", new JSONObject(dono.toJson()));

        JSONArray contatosArray = new JSONArray();
        for (Pessoa contato : contatos) {
            contatosArray.put(new JSONObject(contato.toJson()));
        }
        jsonObject.put("contatos", contatosArray);

        return jsonObject.toString();
    }

    public String toXml() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dono", new JSONObject(dono.toJson()));

        JSONArray contatosArray = new JSONArray();
        for (Pessoa contato : contatos) {
            contatosArray.put(new JSONObject(contato.toJson()));
        }
        jsonObject.put("contatos", contatosArray);

        return XML.toString(jsonObject, "ListaDeContatos");
    }

}
