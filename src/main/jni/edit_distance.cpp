#include "edit_distance.h"
#include <vector>


int FooLib::levenshtein(std::string_view seq1, std::string_view seq2)
{
    if (seq1.size() > seq2.size())
    {
        return FooLib::levenshtein(seq2, seq1);
    }

    int min_size = (int)seq1.size();
    int max_size = (int)seq2.size();
    std::vector<int> lev_dist(min_size + 1);

    for (int i = 0; i <= min_size; i++)
    {
        lev_dist[i] = i;
    }

    for (int j = 1; j <= max_size; j++)
    {
        int previous_diagonal = lev_dist[0];
        int previous_diagonal_save;
        lev_dist[0]++;

        for (int i = 1; i <= min_size; i++)
        {
            previous_diagonal_save = lev_dist[i];
            if (seq1[i - 1] == seq2[j - 1])
            {
                lev_dist[i] = previous_diagonal;
            }
            else
            {
                lev_dist[i] = std::min(std::min(lev_dist[i - 1], lev_dist[i]), previous_diagonal) + 1;
            }
            previous_diagonal = previous_diagonal_save;
        }
    }

    return lev_dist[min_size];
}
