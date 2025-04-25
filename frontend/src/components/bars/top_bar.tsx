import React from "react";
import { View, Image, StyleSheet, Text, TouchableOpacity } from "react-native";
import { Route } from "react-native-tab-view";

type CustomAppBarProps = {
  tabController?: {
    index: number;
    routes: Route[];
  };
  onIndexChange?: (index: number) => void;
};

const TopBar: React.FC<CustomAppBarProps> = ({
  tabController,
  onIndexChange,
}) => {
  const hasTabs = tabController != null;

  const renderTabBar = (props: any) => (
    <View style={styles.tabBar}>
      {props.navigationState.routes.map((route: Route, index: number) => (
        <TouchableOpacity
          key={route.key}
          style={styles.tab}
          onPress={() => onIndexChange?.(index)}
        >
          <Text
            style={[
              styles.tabLabel,
              {
                color: "white",
              },
            ]}
          >
            {route.title}
          </Text>
          {index === props.navigationState.index && (
            <View style={styles.indicator} />
          )}
        </TouchableOpacity>
      ))}
    </View>
  );

  return (
    <View style={styles.appBar}>
      <View style={styles.logoContainer}>
        <Image
          source={require("../../assets/images/logo-app.png")}
          style={styles.logo}
          resizeMode="contain"
        />
      </View>
      {hasTabs && renderTabBar({ navigationState: tabController })}
    </View>
  );
};

const styles = StyleSheet.create({
  appBar: {
    backgroundColor: "#FAB603",
    paddingTop: 10,
  },
  logoContainer: {
    padding: 8,
    alignItems: "center",
  },
  logo: {
    height: 60,
  },
  tabBar: {
    flexDirection: "row",
    justifyContent: "space-around",
    backgroundColor: "transparent",
  },
  tab: {
    flex: 1,
    alignItems: "center",
    paddingVertical: 8,
    position: "relative",
  },
  tabLabel: {
    fontSize: 18,
    fontWeight: "bold",
    textAlign: "center",
  },
  indicator: {
    position: "absolute",
    bottom: 0,
    height: 3,
    width: "100%",
    backgroundColor: "#0077F8",
    borderRadius: 2,
  },
});

export default TopBar;
