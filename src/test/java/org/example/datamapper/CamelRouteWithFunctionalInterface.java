package org.example.datamapper;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class CamelRouteWithFunctionalInterface extends CamelTestSupport {

    @Test
    public void testApacheCamelRoute() throws Exception {
        context.addRoutes(routeBuilder());
        context.start();
        template.send("direct:insert_into_database", new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                exchange.getIn().setHeader("type", "student");
                exchange.getIn().setHeader("studentName", "Melanie");
                exchange.getIn().setHeader("studentAge", "21");
            }
        });

        template.send("direct:insert_into_database", new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                exchange.getIn().setHeader("type", "teacher");
                exchange.getIn().setHeader("teacherName", "Cornelius Rex");
                exchange.getIn().setHeader("teacherAge", "42");
                exchange.getIn().setHeader("teacherSalary", "52000");
            }
        });
    }

    public RouteBuilder routeBuilder(){
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:insert_into_database")
                        .process().exchange(exchange -> exchange.getIn().setBody(ExchangeConversionsToStoreable.convertExchangeToStoreable(exchange)))
                        .process().exchange(exchange -> GenericMapperInterface.create((Storeable) exchange.getIn().getBody()));
            }
        };
    }
}

interface ExchangeConversionsToStoreable{

    static Storeable convertExchangeToStoreable(Exchange exchange){
        switch(exchange.getIn().getHeader("type").toString()){
            case "teacher" : return convertExchangeToTeacher(exchange);
            case "student" : return convertExchangeToStudent(exchange);
            default: return null;
        }
    }

    static Student convertExchangeToStudent(Exchange exchange){
        Student student = new Student();
        student.name = exchange.getIn().getHeader("studentName").toString();
        student.age = exchange.getIn().getHeader("studentAge").toString();
        return student;
    }

    static Teacher convertExchangeToTeacher(Exchange exchange){
        Teacher teacher = new Teacher();
        teacher.name = exchange.getIn().getHeader("teacherName").toString();
        teacher.age = exchange.getIn().getHeader("teacherAge").toString();
        teacher.salary = exchange.getIn().getHeader("teacherSalary").toString();
        return teacher;
    }

}