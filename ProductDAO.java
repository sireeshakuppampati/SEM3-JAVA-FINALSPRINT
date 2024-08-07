import java.sql.*;
import java.util.*;

public class ProductDAO {
    private Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    public void addProduct(Product product) throws SQLException {
        String query = "INSERT INTO products (name, price, quantity, seller_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getQuantity());
            stmt.setInt(4, product.getSellerId());
            stmt.executeUpdate();
        }
    }

    // Other CRUD methods

    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getInt("quantity"), rs.getInt("seller_id")));
            }
        }
        return products;
    }

    public Product getProductById(int id) throws SQLException {
        String query = "SELECT * FROM products WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getInt("quantity"), rs.getInt("seller_id"));
            }
        }
        return null;
    }

    public List<Product> getProductsBySellerId(int sellerId) throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE seller_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, sellerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getInt("quantity"), rs.getInt("seller_id")));
            }
        }
        return products;
    }

    public void updateProduct(Product product) throws SQLException {
        String query = "UPDATE products SET name = ?, price = ?, quantity = ?, seller_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getQuantity());
            stmt.setInt(4, product.getSellerId());
            stmt.setInt(5, product.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteProduct(int id) throws SQLException {
        String query = "DELETE FROM products WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void deleteProductsBySellerId(int sellerId) throws SQLException {
        String query = "DELETE FROM products WHERE seller_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, sellerId);
            stmt.executeUpdate();
        }
    }

    public void deleteAllProducts() throws SQLException {
        String query = "DELETE FROM products";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.executeUpdate();
        }
    }

    public void deleteAllProductsBySellerId(int sellerId) throws SQLException {
        String query = "DELETE FROM products WHERE seller_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, sellerId);
            stmt.executeUpdate();
        }
    }

    public void deleteAllProductsBySellerIds(List<Integer> sellerIds) throws SQLException {
        String query = "DELETE FROM products WHERE seller_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            for (int sellerId : sellerIds) {
                stmt.setInt(1, sellerId);
                stmt.executeUpdate();
            }
        }
    }

    public void deleteAllProductsBySellerIdsBatch(List<Integer> sellerIds) throws SQLException {
        String query = "DELETE FROM products WHERE seller_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            for (int sellerId : sellerIds) {
                stmt.setInt(1, sellerId);
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    public void deleteAllProductsBySellerIdsTransaction(List<Integer> sellerIds) throws SQLException {
        connection.setAutoCommit(false);
        String query = "DELETE FROM products WHERE seller_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            for (int sellerId : sellerIds) {
                stmt.setInt(1, sellerId);
                stmt.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public void deleteAllProductsBySellerIdsTransactionBatch(List<Integer> sellerIds) throws SQLException {
        connection.setAutoCommit(false);
        String query = "DELETE FROM products WHERE seller_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            for (int sellerId : sellerIds) {
                stmt.setInt(1, sellerId);
                stmt.addBatch();
            }
            stmt.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public void deleteAllProductsBySellerIdsTransactionBatchNoAutoCommit(List<Integer> sellerIds) throws SQLException {
        connection.setAutoCommit(false);
        String query = "DELETE FROM products WHERE seller_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            for (int sellerId : sellerIds) {
                stmt.setInt(1, sellerId);
                stmt.addBatch();
            }
            stmt.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    public void deleteAllProductsBySellerIdsTransactionBatchNoAutoCommitNoRollback(List<Integer> sellerIds) throws SQLException {
        connection.setAutoCommit(false);
        String query = "DELETE FROM products WHERE seller_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            for (int sellerId : sellerIds) {
                stmt.setInt(1, sellerId);
                stmt.addBatch();
            }
            stmt.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public void deleteAllProductsBySellerIdsTransactionBatchNoAutoCommitNoRollbackNoFinally(List<Integer> sellerIds) throws SQLException {
        connection.setAutoCommit(false);
        String query = "DELETE FROM products WHERE seller_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            for (int sellerId : sellerIds) {
                stmt.setInt(1, sellerId);
                stmt.addBatch();
            }
            stmt.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    public void deleteAllProductsBySellerIdsTransactionBatchNoAutoCommitNoRollbackNoFinallyNoThrow(List<Integer> sellerIds) throws SQLException {
        connection.setAutoCommit(false);
        String query = "DELETE FROM products WHERE seller_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            for (int sellerId : sellerIds) {
                stmt.setInt(1, sellerId);
                stmt.addBatch();
            }
            stmt.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
    }

    public void deleteAllProductsBySellerIdsTransactionBatchNoAutoCommitNoRollbackNoFinallyNoThrowNoConnection(List<Integer> sellerIds) throws SQLException {
        connection.setAutoCommit(false);
        String query = "DELETE FROM products WHERE seller_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            for (int sellerId : sellerIds) {
                stmt.setInt(1, sellerId);
                stmt.addBatch();
            }
            stmt.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public void deleteAllProductsBySellerIdsTransactionBatchNoAutoCommitNoRollbackNoFinallyNoThrowNoConnectionNoPrepareStatement(List<Integer> sellerIds) throws SQLException {
        connection.setAutoCommit(false);
        String query = "DELETE FROM products WHERE seller_id = ?";
        try {
            for (int sellerId : sellerIds) {
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.setInt(1, sellerId);
                stmt.addBatch();
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    

}
