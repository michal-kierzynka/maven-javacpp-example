#ifndef LP_ALIGNMENT_SRC_EDIT_DISTANCE_H_
#define LP_ALIGNMENT_SRC_EDIT_DISTANCE_H_

#include <string_view>

namespace FooLib
{
int __attribute__((visibility("default"))) levenshtein(std::string_view seq1, std::string_view seq2);
}

#endif //LP_ALIGNMENT_SRC_EDIT_DISTANCE_H_
