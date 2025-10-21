package com.utils;

/**
 * 类说明 : 基于用户的协同过滤算法
 */

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class UserBasedCollaborativeFiltering {

    private Map<String, Map<String, Double>> userRatings;
    private Map<String, List<String>> itemUsers;
    private Map<String, Integer> userIndex;
    private Map<Integer, String> indexUser;
    private Long[][] sparseMatrix;

    public UserBasedCollaborativeFiltering(Map<String, Map<String, Double>> userRatings) {
        this.userRatings = userRatings;
        this.itemUsers = new HashMap<>();

        this.userIndex = new HashMap<>();//辅助存储每一个用户的用户索引index映射:user->index
        this.indexUser = new HashMap<>();//辅助存储每一个索引index对应的用户映射:index->user

        // 构建物品-用户倒排表
        int keyIndex = 0;
        for (String user : userRatings.keySet()) {
            Map<String, Double> ratings = userRatings.get(user);
            for (String item : ratings.keySet()) {
                if (!itemUsers.containsKey(item)) {
                    itemUsers.put(item, new ArrayList<>());
                }
                itemUsers.get(item).add(user);
            }
            //用户ID与稀疏矩阵建立对应关系
            this.userIndex.put(user, keyIndex);
            this.indexUser.put(keyIndex, user);
            keyIndex++;
        }

        int N = userRatings.size();
        this.sparseMatrix = new Long[N][N];//建立用户稀疏矩阵，用于用户相似度计算【相似度矩阵】
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                this.sparseMatrix[i][j] = (long) 0;
        }
        for (String item : itemUsers.keySet()) {
            List<String> userList = itemUsers.get(item);
            for (String u1 : userList) {
                for (String u2 : userList) {
                    if (u1.equals(u2)) {
                        continue;
                    }
                    this.sparseMatrix[this.userIndex.get(u1)][this.userIndex.get(u2)] += 1;
                }
            }
        }

    }

    public double calculateSimilarity(String user1, String user2) {
        //计算用户之间的相似度【余弦相似性】
        Integer id1 = this.userIndex.get(user1);
        Integer id2 = this.userIndex.get(user2);
        if (id1 == null || id2 == null) return 0.0;
        return this.sparseMatrix[id1][id2] / Math.sqrt(userRatings.get(indexUser.get(id1)).size() * userRatings.get(indexUser.get(id2)).size());
    }

    public List<String> recommendItems(String targetUser, int numRecommendations) {
        // 计算目标用户与其他用户的相似度
        Map<String, Double> userSimilarities = new HashMap<>();
        for (String user : userRatings.keySet()) {
            if (!user.equals(targetUser)) {
                double similarity = calculateSimilarity(targetUser, user);
                userSimilarities.put(user, similarity);
            }
        }

        // 根据相似度进行排序
        List<Map.Entry<String, Double>> sortedSimilarities = new ArrayList<>(userSimilarities.entrySet());
        sortedSimilarities.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // 选择相似度最高的K个用户
        List<String> similarUsers = new ArrayList<>();
        for (int i = 0; i < numRecommendations; i++) {
            if (i < sortedSimilarities.size()) {
                similarUsers.add(sortedSimilarities.get(i).getKey());
            } else {
                break;
            }
        }

        // 获取相似用户喜欢的物品，并进行推荐
        Map<String, Double> recommendations = new HashMap<>();
        for (String user : similarUsers) {
            Map<String, Double> ratings = userRatings.get(user);
            for (String item : ratings.keySet()) {
                if (userRatings.get(targetUser) != null && !userRatings.get(targetUser).containsKey(item)) {
                    recommendations.put(item, ratings.get(item));
                }
            }
        }

        // 排序推荐物品
        LinkedHashMap<String, Double> sortedRecommendations = new LinkedHashMap<>(recommendations);
        // 取前N个推荐物品
        int numItems = Math.min(numRecommendations, sortedRecommendations.size());
        sortedRecommendations = sortedRecommendations.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Double>comparingByValue().reversed())).limit(numItems)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        List<String> result = new ArrayList<String>();
        result.addAll(sortedRecommendations.keySet());
        return result;
    }

///////////////////////////////////////////////以下为新版本协同推荐算法逻辑///////////////////////////////////////////////////////

    public UserBasedCollaborativeFiltering() {

    }


    /**
     * 为当前用户推荐商品
     *
     * @param entities
     * @param x
     * @param y
     * @param numRecommendations
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */

    public List<String> recommendItems(List entities, String x, String y, String xValue, int numRecommendations) {
        List<String> recommendations = new ArrayList<>();
        try {
            // 构建稀疏用户-商品矩阵
            Map<String, Map<String, Double>> userProductMatrix = buildSparseUserProductMatrix(entities, x, y);

            // 计算当前用户的相似度得分
            Map<String, Double> similarityScores = calculateSimilarityScores(userProductMatrix, xValue);

            // 为当前用户生成推荐商品列表
            recommendations = generateRecommendations(userProductMatrix, similarityScores, xValue, numRecommendations);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return recommendations;
        }

    }

    /**
     * 构建稀疏用户-商品矩阵，使用反射来获取属性值
     *
     * @param entities 实体列表，可以是收藏表或其他表的实体
     * @param x        用户ID的字段名
     * @param y        商品ID的字段名
     * @return 稀疏矩阵，键为用户ID，值为另一个Map，其中键为商品ID，值为收藏标记（1）
     * @throws NoSuchFieldException   如果指定的字段名不存在
     * @throws IllegalAccessException 如果无法访问字段
     */
    private Map<String, Map<String, Double>> buildSparseUserProductMatrix(
            List<Object> entities, String x, String y)
            throws NoSuchFieldException, IllegalAccessException {
        Map<String, Map<String, Double>> matrix = new HashMap<>();
        for (Object entity : entities) {
            // 通过反射获取字段
            Field userIdFld = entity.getClass().getDeclaredField(x);
            Field refIdFld = entity.getClass().getDeclaredField(y);
            // 设置可访问性
            userIdFld.setAccessible(true);
            refIdFld.setAccessible(true);
            // 获取字段值
            String userId = userIdFld.get(entity) + "";
            String refId = refIdFld.get(entity) + "";
            // 使用computeIfAbsent确保每个用户ID都有一个对应的Map
            matrix.computeIfAbsent(userId, k -> new HashMap<>()).put(refId, 1.0);
        }
        return matrix;
    }

    /**
     * 计算用户之间的相似度得分
     *
     * @param userProductMatrix 稀疏用户-商品矩阵
     * @param currentUserid     当前用户ID
     * @return 相似度矩阵，键为其他用户ID，值为与当前用户的相似度得分
     */
    private Map<String, Double> calculateSimilarityScores(Map<String, Map<String, Double>> userProductMatrix, String currentUserid) {
        Map<String, Double> currentUserPrefs = userProductMatrix.get(currentUserid);
        Map<String, Double> similarityScores = new HashMap<>();

        for (Map.Entry<String, Map<String, Double>> entry : userProductMatrix.entrySet()) {
            String otherUserid = entry.getKey();
            if (!otherUserid.equals(currentUserid)) {
                Map<String, Double> otherUserPrefs = entry.getValue();
                double similarity = calculateCosineSimilarity(currentUserPrefs, otherUserPrefs);
                if (similarity > 0) {
                    similarityScores.put(otherUserid, similarity);
                }
            }
        }
        return similarityScores;
    }

    /**
     * 计算两个用户之间的余弦相似度
     *
     * @param user1Prefs 第一个用户的商品偏好
     * @param user2Prefs 第二个用户的商品偏好
     * @return 余弦相似度得分
     */
    private double calculateCosineSimilarity(Map<String, Double> user1Prefs, Map<String, Double> user2Prefs) {
        // 计算两个用户的共同偏好商品
        Set<String> commonKeys = new HashSet<>(user1Prefs.keySet());
        commonKeys.retainAll(user2Prefs.keySet());

        // 如果没有共同偏好，则相似度为0
        if (commonKeys.isEmpty()) {
            return 0.0;
        }

        // 计算点积
        double dotProduct = commonKeys.stream()
                .mapToDouble(key -> user1Prefs.get(key) * user2Prefs.get(key))
                .sum();

        // 计算两个用户的偏好向量的范数
        double normUser1 = Math.sqrt(user1Prefs.values().stream().mapToDouble(i -> i * i).sum());
        double normUser2 = Math.sqrt(user2Prefs.values().stream().mapToDouble(i -> i * i).sum());

        // 返回余弦相似度
        return dotProduct / (normUser1 * normUser2);
    }

    /**
     * 为当前用户生成推荐商品列表
     *
     * @param userProductMatrix  稀疏用户-商品矩阵
     * @param similarityScores   相似度矩阵
     * @param currentUserid      当前用户ID
     * @param numRecommendations 推荐商品的数量
     * @return 推荐商品ID列表
     */
    public List<String> generateRecommendations(Map<String, Map<String, Double>> userProductMatrix, Map<String, Double> similarityScores, String currentUserid, int numRecommendations) {
        Map<String, Double> productScores = new HashMap<>();

        for (Map.Entry<String, Double> similarUser : similarityScores.entrySet()) {
            String similarUserid = similarUser.getKey();
            double similarityScore = similarUser.getValue();
            Map<String, Double> similarUserPrefs = userProductMatrix.get(similarUserid);

            for (Map.Entry<String, Double> productEntry : similarUserPrefs.entrySet()) {
                String productid = productEntry.getKey();
                // 只考虑当前用户未收藏的商品
                if (!userProductMatrix.getOrDefault(currentUserid, Collections.emptyMap()).containsKey(productid)) {
                    productScores.merge(productid, similarityScore, Double::sum);
                }
            }
        }

        // 根据商品得分进行排序，并返回前numRecommendations个商品
        return productScores.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(numRecommendations)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }


}


