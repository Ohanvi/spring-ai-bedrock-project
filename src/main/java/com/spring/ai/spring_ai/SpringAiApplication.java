package com.spring.ai.spring_ai;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.client.transport.HttpClientSseClientTransport;
import org.springframework.ai.chat.client.ChatClient;

import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.model.bedrock.titan.autoconfigure.BedrockTitanEmbeddingAutoConfiguration;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
//Exclude some auto-configuration classes if necessary
@EnableAutoConfiguration(exclude = {BedrockTitanEmbeddingAutoConfiguration.class})
public class SpringAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAiApplication.class, args);
	}

	@Bean
	McpSyncClient mcpSyncClient(){
		var mcp = McpClient
				.sync(HttpClientSseClientTransport.builder("http://localhost:8081").build())
				.build();
		mcp.initialize();
		return mcp;
	}

	@Bean
	ChatClient chatClient(ChatClient.Builder builder,
						  McpSyncClient mcpSyncClient,
						  DogRepository dogRepository/*,
						  VectorStore vectorStore*/) {
		dogRepository.findAll().forEach(dog -> {
			var document = new Document("id: %s, name: %s, description: %s"
					.formatted(dog.id(), dog.name(), dog.description()));
			/*vectorStore.add(List.of(document));*/
		});



		var system = """
				you are an AI powered assistant to help people adopt a dog from the adoption
				agency named Pooch Palace with locations in Antrwerp, Seaul, Tokyo, Signpore, Paris, Mumbai, New Delhi, Barcelona, San Franciso, and London. Information about the dogs available will be presented below.
				If there is no information, then return a polite response suggesting we do not have any dogs available.
				""";
		return builder
				.defaultSystem(system)
				.defaultToolCallbacks(new SyncMcpToolCallbackProvider(mcpSyncClient))
				.build();
	}


}


/*@Component
class DogAdoptionScheduler{

	private final ObjectMapper objectMapper;

	DogAdoptionScheduler(ObjectMapper objectMapper){
		this.objectMapper = objectMapper;
	}
	@Tool(description = "schedule an appointment to adopt a " +
			"dog at Pooch Palace agenecy")
	String scheduleDogAdoption(
			@ToolParam(description = "the id of the dog id") int dogId,
			@ToolParam(description = "name of the dog") String dogName) throws JsonProcessingException {
		System.out.println("confirming appointment for [" +
				dogId+"] and [" +
				dogName +"]");
		// Logic to schedule dog adoption
		var instant = Instant.now().plus(3, ChronoUnit.DAYS);
		return objectMapper.writeValueAsString(instant);
	}
}*/

@Controller
@ResponseBody
class ChatClientController {

	private final EmbeddingModel embeddingModel;
/*	private final DogAdoptionScheduler dogAdoptionScheduler;*/

	private final ChatClient singularity;

	/*private final QuestionAnswerAdvisor questionAnswerAdvisor;*/
	private final Map<String, PromptChatMemoryAdvisor> advisorMap = new ConcurrentHashMap<>();

	public ChatClientController(
			/*DogAdoptionScheduler dogAdoptionScheduler,*/
			ChatClient singularity,
								EmbeddingModel embeddingModel/*,
								VectorStore vectorStore*/) {
		this.singularity = singularity;
		this.embeddingModel = embeddingModel;
		/*this.dogAdoptionScheduler = dogAdoptionScheduler;*/
		/*this.questionAnswerAdvisor = new QuestionAnswerAdvisor(vectorStore);*/
	}

	@GetMapping("/{user}/inquire")
	String inquire(@PathVariable("user") String user,
				   @RequestParam String question){

		MessageWindowChatMemory memory = MessageWindowChatMemory.builder()
				.chatMemoryRepository(new InMemoryChatMemoryRepository())
				.maxMessages(30)
				.build();

		var advisor = this.advisorMap.computeIfAbsent(user,
				_ -> PromptChatMemoryAdvisor.builder(memory).build());


		return this.singularity.prompt()
				.user(question)
				//.tools(dogAdoptionScheduler)
				.advisors(advisor/*, this.questionAnswerAdvisor*/)
				.call()
				.content();
	}

	// Define endpoints for dog operations
}

interface DogRepository extends ListCrudRepository<Dog, Integer> {
	// Define methods for dog repository
}

record Dog(@Id int id, String name, String owner, String description) {
	// Record class for Dog
}