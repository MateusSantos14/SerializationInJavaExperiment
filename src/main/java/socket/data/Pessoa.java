package socket.data;

import org.json.JSONObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.io.StringWriter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement
	public String nome;

	@XmlElement
	public String cidade;

	@XmlElement
	public long telefone;

	@XmlElement
	public int ano;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public long getTelefone() {
		return telefone;
	}

	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Pessoa(){

	}

	public Pessoa(String nome, String cidade, long telefone, int ano) {
		this.nome = nome;
		this.cidade = cidade;
		this.telefone = telefone;
		this.ano = ano;
	}

	@Override
	public String toString() {
		return this.nome + "," + this.cidade + "," + this.telefone + "," + this.ano;
	}

	public String toJson() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("nome", this.nome);
		jsonObject.put("cidade", this.cidade);
		jsonObject.put("telefone", this.telefone);
		jsonObject.put("ano", this.ano);
		return jsonObject.toString(4); // Indentação de 4 espaços
	}

	public String toXml() throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Pessoa.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		StringWriter writer = new StringWriter();
		marshaller.marshal(this, writer);
		return writer.toString();
	}
}