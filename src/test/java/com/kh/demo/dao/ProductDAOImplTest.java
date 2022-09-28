package com.kh.demo.dao;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ProductDAOImplTest {

  @Autowired
  private ProductDAO productDAO;

  @Test
  @DisplayName("상품저장")
  void save() {
    Product product = new Product();
    product.setPname("컴퓨터");
    product.setQuantity(15L);
    product.setPrice(15000L);

    Long saveProduct = productDAO.save(product);
    log.info("saveProduct={}",saveProduct);
    Assertions.assertThat(saveProduct).isEqualTo(1);
  }

  @Test
  @DisplayName("상품조회")
  void findByProductId() {
    Long productId = 1L;

    Optional<Product> findProduct = productDAO.findByProductId(productId);
    Assertions.assertThat(findProduct.get().getPname()).isEqualTo("컴퓨터");
    Assertions.assertThat(findProduct.get().getQuantity()).isEqualTo(15L);
    Assertions.assertThat(findProduct.get().getPrice()).isEqualTo(15000L);
  }

  @Test
  @DisplayName("상품목록")
  void findAll() {
    List<Product> list = productDAO.findAll();
    log.info("상품수={}",list.size());
    list.stream().forEach(item->log.info(list.toString()));
  }


  @Test
  @DisplayName("상품수정")
  void update() {
    Long productId = 1L;
    Product product = new Product();
    product.setPname("마우스");
    product.setQuantity(20L);
    product.setPrice(20000L);

    int updateProduct = productDAO.update(productId, product);

    Optional<Product> findProduct = productDAO.findByProductId(productId);
    Assertions.assertThat(findProduct.get().getPname()).isEqualTo("마우스");
    Assertions.assertThat(findProduct.get().getQuantity()).isEqualTo(20L);
    Assertions.assertThat(findProduct.get().getPrice()).isEqualTo(20000L);
  }

  @Test
  @DisplayName("상품삭제")
  void deleteByProductId() {
    Long productId = 1L;

    productDAO.deleteByProductId(productId);
    Optional<Product> findProduct = productDAO.findByProductId(productId);
    Assertions.assertThat(findProduct.isEmpty()).isTrue();
  }
}