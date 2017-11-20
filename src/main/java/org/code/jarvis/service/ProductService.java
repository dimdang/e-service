package org.code.jarvis.service;

import org.code.jarvis.model.core.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Dang Dim
 * Date     : 20-Nov-17, 1:58 PM
 * Email    : d.dim@gl-f.com
 */
public interface ProductService extends PagingAndSortingRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);
}
