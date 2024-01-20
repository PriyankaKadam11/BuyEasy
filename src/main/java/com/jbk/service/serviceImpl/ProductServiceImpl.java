package com.jbk.service.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.dao.daoImpl.ProductDaoImpl;
import com.jbk.entity.ProductEntity;
import com.jbk.exception.ResourseNotExistException;
import com.jbk.model.CategoryModel;
import com.jbk.model.ProductModel;
import com.jbk.model.ProductModelReport;
import com.jbk.model.SupplierModel;
import com.jbk.service.ProductService;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDaoImpl product;

	@Autowired
	private ModelMapper mapper;

	@Override
	public int addProduct(ProductModel productmodel) {
		return product.addProduct(mapper.map(productmodel, ProductEntity.class));
	}

	@Override
	public ProductModel getProductById(long productId) {
		ProductModel map = null;
		ProductEntity productById = product.getProductById(productId);
		if (productById != null) {
			map = mapper.map(productById, ProductModel.class);
		}
		return map;
	}

	@Override
	public List<ProductModel> getAllProducts() {
		List<ProductModel> model = new ArrayList<>();
		List<ProductEntity> allProducts = product.getAllProducts();
		for (ProductEntity productEntity : allProducts) {
			ProductModel map = mapper.map(productEntity, ProductModel.class);
			model.add(map);
		}
		//allProducts.stream().map(list->allProducts(list,ProductModel.class)).collect(Collectors.toList());
		return model;
	}

	@Override
	public void updateProduct(ProductModel productmodel) {
		ProductEntity productById = product.getProductById(productmodel.getProductId());
		if (productById != null) {
				product.updateProduct(mapper.map(productmodel, ProductEntity.class));
		} else {
			throw new ResourseNotExistException("product not exist with id " + productmodel.getProductId());
		}
		
	}

	@Override
	public int deleteProduct(long productId) {
		return product.deleteProduct(productId);
	}

	@Override
	public List<ProductModel> sortProductByName() {
		List<ProductEntity> sortProductByName = product.sortProductByName();
		List<ProductModel> list =null;
		if(sortProductByName!=null && !sortProductByName.isEmpty()) {
			//for (ProductEntity entity : sortProductByName) {
			list = sortProductByName.stream().map(entity ->mapper.map(entity, ProductModel.class)).collect(Collectors.toList());
		//}
		}
		return list;
	}

	@Override
	public double getMaxPrice() {
		return product.getMaxPrice();
	}

	@Override
	public List<ProductModel> getMaxPriceProduct() {
		List<ProductEntity> maxPriceProduct = product.getMaxPriceProduct();
		List<ProductModel> productModel=null;
		if(maxPriceProduct!=null && !maxPriceProduct.isEmpty()) {
			
				productModel= maxPriceProduct.stream().map(entity1 ->mapper.map(entity1, ProductModel.class)).collect(Collectors.toList());
		  
		}else {
			throw new ResourseNotExistException("product not exist");
		}
		return productModel 	;
	}

	@Override
	public String uploadFile(MultipartFile file) {
		String fileName=file.getOriginalFilename();
		String folderName="FileUploads";
		String msg=null;
		try {
			FileOutputStream fos=new FileOutputStream(folderName+File.separator+fileName);
			byte[] data = file.getBytes();
			fos.write(data);
			msg="uploaded";
			
			//read data
			List<ProductModel> excel = readExcel(folderName+File.separator+fileName);
			for (ProductModel productModel : excel) {
				addProduct(productModel);
			}
			
		} catch (Exception e) {
	       msg="upload fail";
			e.printStackTrace();
		}
		return msg;
	}
	
	public List<ProductModel> readExcel(String filePath){
		List<ProductModel> list=new ArrayList<>();
		try {
			FileInputStream fis=new FileInputStream(filePath);
			Workbook workbook=new XSSFWorkbook(filePath);
			Sheet sheetAt = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheetAt.rowIterator();
			while (rowIterator.hasNext()) {
				Row row = (Row) rowIterator.next();
				int rowNum = row.getRowNum();
				if(rowNum==0) {
					continue;
				}
				ProductModel productModel=new ProductModel();
				String productId = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
				productModel.setProductId(Long.parseLong(productId+rowNum));
				
				Iterator<Cell> cellIterator = row.cellIterator();
				
				while (cellIterator.hasNext()) {
					Cell cell = (Cell) cellIterator.next();
					
					int columnIndex = cell.getColumnIndex();
					
					switch(columnIndex) {
					
					case 0:
					{
						productModel.setProductname(cell.getStringCellValue());
						break;
					}
					
					case 1:
					{
						SupplierModel suppliermodel=new SupplierModel();
						suppliermodel.setSupplierId((int)cell.getNumericCellValue());
						productModel.setSupplier(suppliermodel);;
						break;
					}
					case 2:
					{
						CategoryModel category=new CategoryModel();
						category.setCategoryId((int)cell.getNumericCellValue());
						productModel.setCategory(category);
						break;
					}
					case 3:
					{
						productModel.setProductQty((int)cell.getNumericCellValue());
						break;
					}
					case 4:
					{
						productModel.setProductprice(cell.getNumericCellValue());
						break;
					}
					case 5:
					{
						productModel.setDeliverycharges((int)cell.getNumericCellValue());
						break;
					}
					}
					
				}
				list.add(productModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return list;	
	}
	
	@Override
	public String generateReport() {
		List<ProductModel> allProducts = getAllProducts();
		List<ProductModelReport> allProductList=new ArrayList<>();
		ProductModelReport productreport=null;
		for (ProductModel productModel : allProducts) {
			productreport=new ProductModelReport();
			productreport.setProductname(productModel.getProductname());
			productreport.setProductprice(productModel.getProductprice());
			productreport.setProductQty(productModel.getProductQty());
			productreport.setDeliverycharges(productModel.getDeliverycharges());
			allProductList.add(productreport);
		}
		String filePath=null;
		try {
			//fetching .jrxml file from resources folder
		  final InputStream resourceAsStream = this.getClass().getResourceAsStream("/Product list.jrxml");
		  
		  //compile the jasper report from .jrxml to .jasper
		  JasperReport compileReport = JasperCompileManager.compileReport(resourceAsStream);
		  
		  //fetching the products from daa sourse
		  final JRBeanCollectionDataSource JRBeanCollectionDataSource=new JRBeanCollectionDataSource(allProductList);
		  
		  //filling the report with product data
		  JasperPrint print = JasperFillManager.fillReport(compileReport, null, JRBeanCollectionDataSource);
		  
		  //give the path where u want store this file
		  filePath=System.getProperty("user.home") + "/Downloads/Productlist.pdf";
		  
		  JasperExportManager.exportReportToPdfFile(print, filePath);
		  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filePath;
	}

}
