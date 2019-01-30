package fdmc.web.servlets;

import fdmc.domain.entities.Cat;
import fdmc.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/cats/profile")
public class CatProfileServlet extends HttpServlet {

    private static final String CAT_PROFILE_HTML_FILE_PATH = "E:\\SoftUni\\Java Web Development Basics\\04. EXERCISE INTRODUCTION TO JAVA EE\\src\\main\\resources\\views\\catProfile.html";
    private static final String CAT_NOT_FOUND_FILE_PATH = "E:\\SoftUni\\Java Web Development Basics\\04. EXERCISE INTRODUCTION TO JAVA EE\\src\\main\\resources\\views\\non-existent-cat.html";

    private final HtmlReader htmlReader;

    @Inject
    public CatProfileServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cat cat = ((Map<String, Cat>) req.getSession().getAttribute("cats"))
                .get(req.getQueryString().split("=")[1]);

        String htmlFileContent;
        if (cat == null) {
            htmlFileContent = this.htmlReader
                    .readHtmlFail(CAT_NOT_FOUND_FILE_PATH)
                    .replace("{{catName}}", req.getQueryString().split("=")[1]);
        } else {
            htmlFileContent = this.htmlReader
                    .readHtmlFail(CAT_PROFILE_HTML_FILE_PATH)
                    .replace("{{catName}}", cat.getName())
                    .replace("{{catBreed}}", cat.getBreed())
                    .replace("{{catColor}}", cat.getColor())
                    .replace("{{catAge}}", cat.getAge().toString());
        }

        resp.getWriter().println(htmlFileContent);
    }
}
