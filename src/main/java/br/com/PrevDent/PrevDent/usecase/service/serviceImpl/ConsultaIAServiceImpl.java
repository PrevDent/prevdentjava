package br.com.PrevDent.PrevDent.usecase.service.serviceImpl;

import br.com.PrevDent.PrevDent.domain.model.Consulta;
import br.com.PrevDent.PrevDent.usecase.service.ConsultaIAService;
import org.springframework.ai.azure.openai.AzureOpenAiChatModel;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultaIAServiceImpl implements ConsultaIAService {

    @Autowired
    private AzureOpenAiChatModel openAiChatClient;


    @Override
    public String gerarResumoConsulta(Consulta consulta) {
        String prompt = String.format("""
            Gere um resumo da seguinte consulta:
            - Dentista: %s
            - Paciente: %s
            - Data: %s
            - Tipo: %s
            """, consulta.getDentista().getNome(),
                consulta.getPaciente().getNome(),
                consulta.getData(),
                consulta.getTipoTratamento());

        Prompt chatPrompt = new Prompt(List.of(new UserMessage(prompt)));

        return openAiChatClient.call(chatPrompt).getResult().getOutput().getText();
    }

    @Override
    public String classificarPrioridade(Consulta consulta) {

        String prompt = String.format("""
            Classifique a prioridade da seguinte consulta (urgente, rotina, follow-up):
            Tipo de tratamento: %s
            """, consulta.getTipoTratamento());

        return openAiChatClient.call(prompt);
    }

    @Override
    public String responderPergunta(String pergunta, List<Consulta> consultas) {

        String contexto = consultas.stream()
                .limit(10)
                .map(c -> String.format(
                        "Consulta em %s: %s com Dr. %s (%s)",
                        c.getData(),
                        c.getTipoTratamento(),
                        c.getDentista().getNome(),
                        c.getPaciente().getNome()))
                .collect(Collectors.joining("\n"));

        String prompt = String.format("""
        Você é um assistente odontológico inteligente. 
        Baseado nestas consultas agendadas:
        %s
        
        Responda de forma clara e profissional:
        %s
        
        Regras:
        - Seja conciso (máximo 2 frases)
        - Mantenha o foco em odontologia
        - Formate datas em PT-BR
        """, contexto, pergunta);

        UserMessage userMessage = new UserMessage(prompt);
        Prompt chatPrompt = new Prompt(List.of(userMessage));

        return openAiChatClient.call(chatPrompt).getResult().getOutput().getText();
    }

    @Override
    public String traduzirConsulta(Consulta consulta, String idiomaDestino) {

        String prompt = String.format("""
            Traduza a seguinte descrição de consulta para %s:
            Dentista: %s, Paciente: %s, Data: %s, Tipo: %s
            """, idiomaDestino, consulta.getDentista().getNome(), consulta.getPaciente().getNome(), consulta.getData(), consulta.getTipoTratamento());

        return openAiChatClient.call(prompt);
    }
}
