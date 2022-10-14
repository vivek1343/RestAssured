package RestAssured;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Main {
    @BeforeMethod
    public void base()
    {
        baseURI="https://reqres.in/api";
    }
    @Test
    public void Restget( String pagenumber,int id)
    {
        given().
                get("/users?page="+pagenumber).
                then().
                statusCode(200).
                body("data[1].id", equalTo(id)).
                log().all();
    }
    @Test
    public void RestPost()
    {
        String name="vivek";
        String job="SDET";
        JSONObject req=new JSONObject();
        req.put("name",name);
        req.put("job",job);
        given().body(req.toJSONString()).
                when().
                post("/users").
                then().
                log().all();
    }
    @Test
    public void Restput()
    {
        JSONObject req=new JSONObject();
        req.put("name","vivek sagala");
        req.put("job","SDET intern");
        given().body(req.toJSONString()).
                when().
                put("/users/2").
                then().
                statusCode(200).
                log().all();
    }
    @Test
    public void Restpatch()
    {
        String name="vivek sagala";
        String job="SDET intern";
        JSONObject req=new JSONObject();
        req.put("name",name);
        req.put("job",job);
        given().contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(req.toJSONString()).
                when().
                patch("/users/3").
                then().
                statusCode(200).
                log().all();
    }
    @Test
    public void RestDelete()
    {
        when().delete("/users/3").then().statusCode(204).log().all();
    }

}
