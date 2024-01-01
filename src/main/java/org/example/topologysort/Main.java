package org.example.topologysort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int v, e;
	static int[] indegree;
	static List<Integer>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());

		indegree = new int[v+1]; //진입차수

		list = new ArrayList[v+1];

		for(int i=1; i<=v; i++){
			list[i] = new ArrayList<>();
		}

		for(int i=0; i<e; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			indegree[b]++;
		}

		topologySort();
	}

	public static void topologySort(){
		StringBuilder sb = new StringBuilder();
		List<Integer> result = new ArrayList<>();
		Queue<Integer> queue = new ArrayDeque<>();

		for(int i=1; i<=v; i++){
			if(indegree[i] == 0){
				queue.offer(i);
			}
		}

		while(!queue.isEmpty()){
			int now = queue.poll();
			result.add(now);

			for(int i : list[now]){
				indegree[i]--;
				if(indegree[i] == 0){
					queue.offer(i);
				}
			}
		}

		for(int i : result){
			sb.append(i).append(" ");
		}

		System.out.println(sb);
	}
}