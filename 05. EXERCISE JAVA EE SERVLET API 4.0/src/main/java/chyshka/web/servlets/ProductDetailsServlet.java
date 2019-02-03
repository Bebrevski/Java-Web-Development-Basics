package chyshka.web.servlets;

import chyshka.domain.models.view.ProductsDetailsViewModel;
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

@WebServlet("/products/details")
public class ProductDetailsServlet extends HttpServlet {
    private static final String PRODUCT_DETAILS_HTML_FILE_PATH = "E:\\SoftUni\\Java Web Development Basics\\05. EXERCISE JAVA EE SERVLET API 4.0\\src\\main\\resources\\views\\details-product.html";

    private final HtmlReader htmlReader;
    private final ModelMapper modelMapper;
    private final ProductService productService;

    @Inject
    public ProductDetailsServlet(HtmlReader htmlReader, ModelMapper modelMapper, ProductService productService) {
        this.htmlReader = htmlReader;
        this.modelMapper = modelMapper;
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductsDetailsViewModel productsDetailsViewModel = this.modelMapper
                .map(this.productService.findProductByName(
                        req.getQueryString().split("=")[1]),
                        ProductsDetailsViewModel.class);

        String htmlContent = this.htmlReader
                .readHtmlFile(PRODUCT_DETAILS_HTML_FILE_PATH)
                .replace("{{productName}}", productsDetailsViewModel.getName())
                .replace("{{productDescription}}", productsDetailsViewModel.getDescription())
                .replace("{{productType}}", productsDetailsViewModel.getType());

        resp.getWriter().println(htmlContent);
    }
}
