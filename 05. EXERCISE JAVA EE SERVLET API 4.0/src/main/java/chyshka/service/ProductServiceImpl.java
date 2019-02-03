package chyshka.service;

import chyshka.domain.entities.Product;
import chyshka.domain.entities.Type;
import chyshka.domain.models.service.ProductServiceModel;
import chyshka.repository.ProductRepository;
import chyshka.util.ModelMapper;

import javax.inject.Inject;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Inject
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveProduct(ProductServiceModel productServiceModel) {
        Product product = this.modelMapper.map(productServiceModel, Product.class);
        product.setType(Type.valueOf(productServiceModel.getType()));

        this.productRepository.save(product);
    }
}
