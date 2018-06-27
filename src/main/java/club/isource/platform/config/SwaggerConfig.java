package club.isource.platform.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
//import static springfox.documentation.schema.AlternateTypeRules.*;
//import static com.google.common.collect.Lists.*;

@Configuration
@ComponentScan(basePackages={"club.isource.platform.controller"}) // swagger 2、在线文档api类所在包
public class SwaggerConfig {
	@Bean
    public Docket platformApi() {
      return new Docket(DocumentationType.SWAGGER_2) // swagger 3、Docket Springfox的主要api配置装置，这里初始化为swagger规范2.0
          .select() // swagger 4、 返回ApiSelectorBuilder实例 对暴露的端点进行细粒度的控制
            .apis(RequestHandlerSelectors.any()) // swagger 5、使用断言确定请求处理的选择，这里使用一个any缺省断言
            .paths(PathSelectors.any()) // swagger 6、使用断言确定路径的选择，这里使用缺省的any断言
            .build() // swagger 7、 配置完apis和path后进行组建选择器
            .apiInfo(apiInfo()) // swagger api 基本信息配置
//          .pathMapping("/") // swagger 8、 配置完servlet路径映射
//          .directModelSubstitute(LocalDate.class, String.class) // swagger 9、 便利规则组装器用string代替日期显示模型属性
//          .genericModelSubstitutes(ResponseEntity.class) // swagger 10、 便利规则组装器用带类型参数的一般类型代替泛型
//          .alternateTypeRules(
//              newRule(typeResolver.resolve(DeferredResult.class,
//                  typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
//                  typeResolver.resolve(WildcardType.class)))
//          .useDefaultResponseMessages(false) //swagger 11、 标识缺省是否需要使用默认http响应代码。
//          .globalResponseMessage(RequestMethod.GET, // swagger 12、对于全局相应重写 这里所有500的GET请求方法的相应
//              newArrayList(new ResponseMessageBuilder()
//                  .code(500)
//                  .message("500 message")
//                  .responseModel(new ModelRef("Error")) // swagger 13、使用MODEL ERROR相应
//                  .build())) 
//          .securitySchemes(newArrayList(apiKey())) // swagger 14、设置安全模式保护api 支持有： ApiKey, BasicAuth and OAuth
//          .securityContexts(newArrayList(securityContext()))// swagger 15、提供操作的安全上下文
//          .enableUrlTemplating(true)// swagger 16、是否禁止url模板
//          .globalOperationParameters(
//              newArrayList(new ParameterBuilder()
//                  .name("someGlobalParameter")
//                  .description("Description of someGlobalParameter")
//                  .modelRef(new ModelRef("string"))
//                  .parameterType("query")
//                  .required(true)
//                  .build()))
//          .tags(new Tag("springboot微服务架构", "微服务架构服务接口文档")) // swagger 17、追加标签定义所有有效的服务
          //.additionalModels(typeResolver.resolve(AdditionalModel.class)) 
          ;
    }
    private ApiInfo apiInfo() {
    	@SuppressWarnings("rawtypes")
		ApiInfo apiinfo = new ApiInfo(
    			"微服务架构在线接口文档", // 题目
    			"api接口文档说明，实现文档在线查看、运行和测试！", // 文档描述
    			"1.0", // 版本号
    			"urn:tos", // termsOfServiceUrl
    			// 联系方式
    			new Contact("gosenkle", // 姓名
    					"http://www.gaoxinguo.net",  // url
    					"gaoxinguo@qq.com"),  // email
    			"", // license
    			"",  // license url
    			new ArrayList<VendorExtension>()); // 有非空判断，给一个非空值
    	return apiinfo;
    }
            
//    @Autowired
//    private TypeResolver typeResolver;
//
//    private ApiKey apiKey() {
//      return new ApiKey("mykey", "api_key", "header"); // swagger 18、使用apikey作为安全策略，这里使用mykey去识别
//    }
//
//    private SecurityContext securityContext() {
//      return SecurityContext.builder()
//          .securityReferences(defaultAuth())
//          .forPaths(PathSelectors.regex("/anyPath.*"))// swagger 19、选择此安全上下文的路径
//          .build();
//    }
//
//    List<SecurityReference> defaultAuth() {
//      AuthorizationScope authorizationScope
//          = new AuthorizationScope("global", "accessEverything");
//      AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//      authorizationScopes[0] = authorizationScope;
//      return newArrayList(
//          new SecurityReference("mykey", authorizationScopes));// swagger 19、这里使用同一个key定义安全模式
//    }

//    @Bean
//    SecurityConfiguration security() {
//      return SecurityConfigurationBuilder.builder()
//          .clientId("test-app-client-id")
//          .clientSecret("test-app-client-secret")
//          .realm("test-app-realm")
//          .appName("test-app")
//          .scopeSeparator(",")
//          .additionalQueryStringParams(null)
//          .useBasicAuthenticationWithAccessCodeGrant(false)
//          .build(); // 用于oauth和apikeys设置的可选的swagger-ui 安全配置
//    }

//    @Bean
//    UiConfiguration uiConfig() {
//      return UiConfigurationBuilder.builder() // 可选的swagger-ui UI配置， 目前仅支持validation url
//          .deepLinking(true)
//          .displayOperationId(false)
//          .defaultModelsExpandDepth(1)
//          .defaultModelExpandDepth(1)
//          .defaultModelRendering(ModelRendering.EXAMPLE)
//          .displayRequestDuration(false)
//          .docExpansion(DocExpansion.NONE)
//          .filter(false)
//          .maxDisplayedTags(null)
//          .operationsSorter(OperationsSorter.ALPHA)
//          .showExtensions(false)
//          .tagsSorter(TagsSorter.ALPHA)
//          .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
//          .validatorUrl(null)
//          .build();
//    }
}
