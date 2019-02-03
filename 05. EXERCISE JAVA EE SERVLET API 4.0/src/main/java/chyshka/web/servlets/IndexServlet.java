package chyshka.web.servlets;

import chyshka.service.ProductService;
import chyshka.util.HtmlReader;
import chyshka.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class IndexServlet extends HttpServlet {
    private static final String INDEX_HTML_FILE_PATH = "E:\\SoftUni\\Java Web Development Basics\\05. EXERCISE JAVA EE SERVLET API 4.0\\src\\main\\resources\\views\\index.html";

    private final ProductService productService;
    private final HtmlReader htmlReader;
    private final ModelMapper modelMapper;

    @Inject
    public IndexServlet(ProductService productService, HtmlReader htmlReader, ModelMapper modelMapper) {
        this.productService = productService;
        this.htmlReader = htmlReader;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String htmlContent = this.htmlReader.readHtmlFile(INDEX_HTML_FILE_PATH);

        res.getWriter().println(htmlContent);
    }
}
