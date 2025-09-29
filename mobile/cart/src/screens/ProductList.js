import { useEffect, useState } from "react";
import { FlatList, ScrollView, StyleSheet, View } from "react-native";
import { Product } from "../components/Product.js";
import { getProducts } from "../services/productsService.js";

export const ProductsList = ({ navigation }) => {
	const [products, setProducts] = useState([]);
	const [loading, setLoading] = useState(true);

	useEffect(() => {
		const loadProducts = async () => {
			try {
				setLoading(true);
				const productsData = await getProducts();
				setProducts(productsData);
			} catch (error) {
				console.error("Erro ao carregar produtos:", error);
			} finally {
				setLoading(false);
			}
		};

		loadProducts();
	}, []);

	const renderProduct = ({ item: product }) => {
		return (
			<Product
				{...product}
				onPress={() => {
					navigation.navigate("ProductDetails", {
						productId: product.id,
					});
				}}
			/>
		);
	};

	return (
		<ScrollView>
			<FlatList
				style={styles.productsList}
				contentContainerStyle={styles.productsListContainer}
				keyExtractor={item => item.id.toString()}
				data={products}
				renderItem={renderProduct}
			/>
		</ScrollView>
	);
};

const styles = StyleSheet.create({
	productsList: {
		backgroundColor: "#eee",
	},
	productsListContainer: {
		backgroundColor: "#eeeeee",
		paddingVertical: 8,
		marginHorizontal: 8,
	},
});
