package com.problems.array;

import java.util.HashMap;
import java.util.Map;

/*
 * Problem links:
 * https://leetcode.com/problems/encode-and-decode-tinyurl/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=VyBOaboQLGc
 *
 * https://neetcode.io/solutions/encode-and-decode-tinyurl
 *
 * https://leetcode.com/discuss/interview-question/system-design/?currentPage=1&orderBy=hot&query=
 * https://leetcode.com/discuss/interview-question/124658/Design-a-URL-Shortener-(-TinyURL-)-System/
 * */
public class EncodeAndDecodeTinyURL {

    // this is more of a system design problem
    public static void main(String[] args) {
        type1();
        type2();
    }

    // it is a better approach
    // we are using the system current timestamp
    // which
    private static void type2() {
        String url = "https://leetcode.com/problems/design-tinyurl";
        Codec2 codec = new Codec2();
        String encodedUrl = codec.encode(url);
        String decodedUrl = codec.decode(encodedUrl);
        System.out.println(encodedUrl + " -> " + decodedUrl);
    }

    public static class Codec2 {
        String baseUrl = "http://tinyurl.com/";
        Map<String, String> map = new HashMap<>();

        // Encodes a URL to a shortened URL.
        // this is basically based on the current system time
        public String encode(String longUrl) {
            int urlHash = longUrl.hashCode();
            // it will generate a unique
            long currentTime = System.currentTimeMillis();
            StringBuilder sb = new StringBuilder();
            while (currentTime > 0) {
                int rem = (int) (currentTime % 10);
                currentTime = currentTime / 10;
                int randomness = (urlHash * rem) % 3;
                char ch = switch (randomness) {
                    case 0 -> 'a';
                    case 1 -> 'A';
                    default -> '0';
                };
                ch += (char) rem;
                sb.append(ch);
            }
            String encodeUrl = baseUrl + sb;
            map.put(encodeUrl, longUrl);
            return encodeUrl;
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            return map.get(shortUrl);
        }
    }

    // kind of brute force

    private static void type1() {
        String url = "https://leetcode.com/problems/design-tinyurl";
        Codec1 codec = new Codec1();
        String encodedUrl = codec.encode(url);
        String decodedUrl = codec.decode(encodedUrl);
        System.out.println(encodedUrl + " -> " + decodedUrl);
    }

    public static class Codec1 {
        String baseUrl = "http://tinyurl.com/";
        Map<String, String> map = new HashMap<>();

        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            String encodeUrl = baseUrl + java.util.UUID.randomUUID().toString().substring(0, 8);
            map.put(encodeUrl, longUrl);
            return encodeUrl;
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            return map.get(shortUrl);
        }
    }
}
