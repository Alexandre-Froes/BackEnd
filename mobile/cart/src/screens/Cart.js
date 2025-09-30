import React from "react";
import { View, Text, FlatList, Button, TouchableOpacity, StyleSheet } from "react-native";
import { numberFormat } from "../services/numberFormat";

const Cart = ({ items, updateItemQuantity, getTotalPrice }) => {

  const handleIncreaseQuantity = (id) => {
    updateItemQuantity(id, 1); 
  };

  const handleDecreaseQuantity = (id) => {
    updateItemQuantity(id, -1); 
  };

  const handleRemoveItem = (id) => {
    updateItemQuantity(id, -999);
  };

  const renderCartItem = ({ item }) => {
    return (
      <View style={styles.itemContainer}>
        <Text>{item.product.name}</Text>
        <Text>{numberFormat(item.product.price)}</Text>
        
        <View style={styles.quantityContainer}>
          <TouchableOpacity onPress={() => handleDecreaseQuantity(item.id)} style={styles.quantityButton}>
            <Text>-</Text>
          </TouchableOpacity>
          
          <Text>{item.qty}</Text>
          
          <TouchableOpacity onPress={() => handleIncreaseQuantity(item.id)} style={styles.quantityButton}>
            <Text>+</Text>
          </TouchableOpacity>
        </View>
        
        <Button title="Remover" onPress={() => handleRemoveItem(item.id)} color="red" />
      </View>
    );
  };

  return (
    <View style={styles.container}>
      <FlatList
        data={items}
        renderItem={renderCartItem}
        keyExtractor={(item) => item.id.toString()}
        contentContainerStyle={styles.list}
      />
      
      <View style={styles.totalContainer}>
        <Text>Total: {numberFormat(getTotalPrice())}</Text>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    padding: 16,
  },
  list: {
    marginBottom: 20,
  },
  itemContainer: {
    marginVertical: 8,
  },
  quantityContainer: {
    flexDirection: "row",
    alignItems: "center",
    marginVertical: 10,
  },
  quantityButton: {
    marginHorizontal: 8,
  },
  totalContainer: {
    marginTop: 20,
    alignItems: "flex-end",
  },
});

export default Cart;
