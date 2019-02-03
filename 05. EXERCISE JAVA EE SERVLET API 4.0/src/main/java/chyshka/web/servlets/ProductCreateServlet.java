package chyshka.web.servlets;

import chyshka.domain.entities.Type;
import chyshka.domain.models.service.ProductServiceModel;
import chyshka.service.ProductService;
import chyshka.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/products/create")
public class ProductCreateServlet extends HttpServlet {
    private static final String CREATE_PRODUCT_HTML_FILE_PATH = "E:\\SoftUni\\Java Web Development Basics\\05. EXERCISE JAVA EE SERVLET API 4.0\\src\\main\\resources\\views\\create-product.html";

    private final ProductService productService;
    private final HtmlReader htmlReader;

    @Inject
    public ProductCreateServlet(ProductService productService, HtmlReader htmlReader) {
        this.productService = productService;
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String htmlContent = this.htmlReader
                .readHtmlFile(CREATE_PRODUCT_HTML_FILE_PATH)
                .replace("{{typeOptions}}", this.formatTypeOptions());

        resp.getWriter().print(htmlContent);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductServiceModel productServiceModel = new ProductServiceModel();
        productServiceModel.setName(req.getParameter("name"));
        productServiceModel.setDescription(req.getParameter("description"));
        productServiceModel.setType(req.getParameter("type"));

        this.productService.saveProduct(productServiceModel);

        resp.sendRedirect("/");
    }

    private String formatTypeOptions() {
        StringBuilder options = new StringBuilder();

        Arrays.stream(Type.values()).forEach(t -> {
            options
                    .append(String.format("<option value=\"%1$s\">%1$s</option>", t.toString()));
        });

        return options.toString();
    }
}
