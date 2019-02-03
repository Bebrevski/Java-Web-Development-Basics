package chyshka.web.servlets;

import chyshka.domain.models.view.AllProductViewModel;
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
import java.util.List;
import java.util.stream.Collectors;

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
        String htmlContent = this.htmlReader
                .readHtmlFile(INDEX_HTML_FILE_PATH)
                .replace("{{listItems}}", this.formatListItems());

        res.getWriter().println(htmlContent);
    }

    private String formatListItems() {
        List<AllProductViewModel> allProducts = this.productService.findAllProducts()
                .stream()
                .map(productServiceModel -> this.modelMapper.map(productServiceModel, AllProductViewModel.class))
                .collect(Collectors.toList());

        StringBuilder listItems = new StringBuilder();

        allProducts.forEach(p -> {
            listItems
                    .append(String.format("<li><a href=\"/products/details?name=%1$s\">%1$s</li>"
                            , p.getName()));
        });

        return listItems.toString().trim();
    }
}
